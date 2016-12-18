package strategy_bl_servlmpl;

import PO.HotelStrategyPO;
import VO.*;
import helper.ParseHelper;
import hotel_bl_serv.HotelBlServ;
import login_bl_serv.LoginBlServ;
import rmi.RemoteHelper;
import strategy_bl_serv.HotelStrategyBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class HotelStrategyBlServlmpl implements HotelStrategyBlServ{
	@Override
	public Iterator<StrategyVO> getStrategy(String hotelName) {
		ArrayList<StrategyVO> strategyVOs = new ArrayList<>();
		ArrayList<HotelStrategyPO> hotelStrategyPOs;
		try {
			hotelStrategyPOs = RemoteHelper.getInstance().getHotelStrategyDataServ().getHotelStrategyList(hotelName);
			hotelStrategyPOs.forEach(hotelStrategyPO -> strategyVOs.add(ParseHelper.toStrategyVO(hotelStrategyPO)));
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return strategyVOs.iterator();
	}

	@Override
	public boolean addStrategy(String hotelName, StrategyVO strategy) {
		boolean success = false;
		try {
			HotelStrategyPO hotelStrategyPO = ParseHelper.toHotelStrategyPO(hotelName,strategy);
			success = RemoteHelper.getInstance().getHotelStrategyDataServ().insertHotelStrategy(hotelStrategyPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean modifyStrategy(String hotelName, StrategyVO strategy) {
		boolean success = false;
		try {
			HotelStrategyPO hotelStrategyPO = ParseHelper.toHotelStrategyPO(hotelName,strategy);
			success = RemoteHelper.getInstance().getHotelStrategyDataServ().modifiedHotelStrategy(hotelStrategyPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

//	@Override
//	/**
//	 * id是啥
//	 */
	private boolean delStrategy(String hotelName, StrategyVO strategy) {
		boolean success = false;
		try {
			HotelStrategyPO hotelStrategyPO = ParseHelper.toHotelStrategyPO(hotelName,strategy);
			success = RemoteHelper.getInstance().getHotelStrategyDataServ().deleteHotelStrategy(hotelStrategyPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delStrategy(String hotelName, String strategyName) {
		if(hotelName == null || strategyName == null) {
			return false;
		}
		StrategyVO strategyVO = this.getOneStrategy(hotelName,strategyName);
		if(strategyVO == null) {
			return false;
		}
		return this.delStrategy(hotelName,strategyVO);
	}

	@Override
	public double getMinDiscount(String hotelName, String userId,Date inTime,int roomNum) {
		if(hotelName == null || userId == null) {
			return 1;
		}
		Iterator<StrategyVO> strategyVOIterator = this.getStrategy(hotelName);
		double min = 1;
		UserVO userVO = LoginBlServ.getInstance().getUserInfo(userId);
		HotelVO hotelVO = HotelBlServ.getInstance().getHotelInfo(hotelName);
		while(strategyVOIterator.hasNext()) {
			StrategyVO strategyVO = strategyVOIterator.next();
			if(strategyVO.getType().equals("birthday")) {
				Date date = ParseHelper.stringToDate(userVO.getAdditionalInfo());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int month = calendar.get(Calendar.MONTH);
				int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				calendar.setTime(inTime);
				if(month == calendar.get(Calendar.MONTH) && dayOfMonth == calendar.get(Calendar.DAY_OF_MONTH)) {
					min = Math.min(min,((BirthdayStrategy) strategyVO).getDiscount());
				}
			}else if(strategyVO.getType().equals("companies") && userVO.getType().equals(UserType.SPECIAL)) {
				Iterator<String> cooperative = hotelVO.getCooperativeEnterprise();
				if(cooperative != null) {
					while(cooperative.hasNext()) {
						if(cooperative.next().equals(userVO.getAdditionalInfo())) {
							min = Math.min(min,((CooperativeStrategy)strategyVO).getDiscount());
							break;
						}
					}
				}
			}else if(strategyVO.getType().equals("date")) {
				DoubleElevenStrategy doubleElevenStrategy = (DoubleElevenStrategy) strategyVO;
				Date start = doubleElevenStrategy.getStartTime();
				Date end = doubleElevenStrategy.getEndTime();
				if(start.getTime() / 86400 <= inTime.getTime() / 86400 && inTime.getTime() / 86400 <= end.getTime() /86400) {
					min = Math.min(min,doubleElevenStrategy.getDiscount());
				}
			}else if(strategyVO.getType().equals("roomnum") && roomNum >= 3) {
				min = Math.min(min,((RoomNumberStrategy)strategyVO).getDiscount());
			}
		}
		return min;
	}

	@Override
	public StrategyVO getOneStrategy(String hotelName, String strategyName) {
		if(hotelName == null || strategyName == null) {
			return null;
		}
		ArrayList<HotelStrategyPO> strategyPOs = new ArrayList<>();
		try {
			strategyPOs = RemoteHelper.getInstance().getHotelStrategyDataServ().getHotelStrategyList(hotelName);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		StrategyVO strategyVO = null;
		for(HotelStrategyPO hotelStrategyPO:strategyPOs) {
			if(hotelStrategyPO.getStrategyName().equals(strategyName));
			strategyVO = ParseHelper.toStrategyVO(hotelStrategyPO);
		}
		return strategyVO;
	}

	@Override
	public boolean modifyStrategy(String hotelName, String strategyName, Date startTime, Date endTime, double discount) {
		if(hotelName == null || strategyName == null) {
			return false;
		}
		StrategyVO strategyVO = this.getOneStrategy(hotelName, strategyName);
		if(strategyVO == null) {
			return false;
		}else if(strategyVO.getType().equals("date")) {
			DoubleElevenStrategy newStrategy = new DoubleElevenStrategy(strategyName,discount,startTime,endTime);
			return this.modifyStrategy(hotelName,newStrategy);
		}else if(strategyVO.getType().equals("companies")) {
			CooperativeStrategy strategy = (CooperativeStrategy) strategyVO;
			CooperativeStrategy newStrategy  =new CooperativeStrategy(strategyName,discount,strategy.getCompanies());
			return this.modifyStrategy(hotelName,newStrategy);
		}else if(strategyVO.getType().equals("roomnum")) {
			RoomNumberStrategy newStrategy = new RoomNumberStrategy(strategyName,discount);
			return this.modifyStrategy(hotelName,newStrategy);
		}else {
			return false;
		}
	}
}
