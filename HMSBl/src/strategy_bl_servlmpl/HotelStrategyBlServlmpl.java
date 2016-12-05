package strategy_bl_servlmpl;

import PO.HotelStrategyPO;
import VO.StrategyVO;
import helper.ParseHelper;
import rmi.RemoteHelper;
import strategy_bl_serv.HotelStrategyBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
}
