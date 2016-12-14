package strategy_bl_servlmpl;

import PO.HotelStrategyPO;
import VO.BirthdayStrategy;
import VO.StrategyVO;
import VO.UserVO;
import helper.ParseHelper;
import login_bl_serv.LoginBlServ;
import rmi.RemoteHelper;
import strategy_bl_serv.HotelStrategyBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class HotelStrategyBlServlmpl implements HotelStrategyBlServ{
	@Override
	public Iterator<StrategyVO> getStrategy(String hotelName) {
		ArrayList<StrategyVO> strategyVOs = new ArrayList<>();
		ArrayList<HotelStrategyPO> hotelStrategyPOs;
		try {
			hotelStrategyPOs = RemoteHelper.getInstance().getHotelStrategyDataServ().getStrategyList();
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
			success = RemoteHelper.getInstance().getHotelStrategyDataServ().insertStrategy(hotelStrategyPO);
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
			success = RemoteHelper.getInstance().getHotelStrategyDataServ().modifiedStrategy(hotelStrategyPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	/**
	 * id是啥
	 */
	public boolean delStrategy(String hotelName, StrategyVO strategy) {
		boolean success = false;
		try {
			HotelStrategyPO hotelStrategyPO = ParseHelper.toHotelStrategyPO(hotelName,strategy);
			success = RemoteHelper.getInstance().getHotelStrategyDataServ().deleteStrategy("id");
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public double getMinDiscount(String hotelName, String userId,int inTime,int outTime) {
		Iterator<StrategyVO> strategyVOIterator = this.getStrategy(hotelName);
		double min = 1;
		while(strategyVOIterator.hasNext()) {
			StrategyVO strategyVO = strategyVOIterator.next();
			if(strategyVO.getType().equals("birthday")) {
				UserVO userVO = LoginBlServ.getInstance().getUserInfo(userId);
				Date date = ParseHelper.stringToDate(userVO.getAdditionalInfo());
				Date today = null;
				try {
					today = RemoteHelper.getInstance().getTimeServ().getTime();
				}catch (RemoteException e) {
					e.printStackTrace();
				}
				int days = (int)(date.getTime() / 86400 - today.getTime() / 86400);
				if(days >= inTime && days <= outTime) {
					min = Math.min(min,((BirthdayStrategy) strategyVO).getDiscount());
				}
			}else if(strategyVO.getType().equals("companies")) {

			}else if(strategyVO.getType().equals("date")) {

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
			strategyPOs = RemoteHelper.getInstance().getHotelStrategyDataServ().getStrategyList(hotelName);
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
}
