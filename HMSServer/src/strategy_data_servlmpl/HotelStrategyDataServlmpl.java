package strategy_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.HotelStrategyDataServ;
import PO.HotelStrategyPO;
import datahelper.DataFactorylmpl;
import datahelperinterface.DataFactory;
import datahelperinterface.HotelStrategyDataHelper;

public class HotelStrategyDataServlmpl implements HotelStrategyDataServ{
	private ArrayList<HotelStrategyPO> list;
	private DataFactory dataFactory;
	private HotelStrategyDataHelper hotelStrategyDataHelper;
	private static HotelStrategyDataServlmpl hotelStrategyDataServlmpl;
	
	public static HotelStrategyDataServlmpl getInstance(){
		if(hotelStrategyDataServlmpl==null){
			hotelStrategyDataServlmpl=new HotelStrategyDataServlmpl();
		}
		return hotelStrategyDataServlmpl;
	}
	
	private HotelStrategyDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			hotelStrategyDataHelper=dataFactory.getHotelStrategyDataHelper();
			list=hotelStrategyDataHelper.getAll();
		}
	}
	
	@Override
	public boolean insertHotelStrategy(HotelStrategyPO strategyPO) throws RemoteException {
		int i=hotelStrategyDataHelper.insert(strategyPO);
		if(i==0)
			return false;
		else{
			list=hotelStrategyDataHelper.getAll();
			return true;
		}
	}
	@Override
	public ArrayList<HotelStrategyPO> getHotelStrategyList(String hotelname) throws RemoteException {
		ArrayList<HotelStrategyPO> res=new ArrayList<HotelStrategyPO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getHotelName().equals(hotelname)){
				res.add(list.get(i));
			}
		}
		return res;
	}

	@Override
	public boolean deleteHotelStrategy(HotelStrategyPO strategyPO) throws RemoteException {
		int i=hotelStrategyDataHelper.delete(strategyPO);
		if(i==0)
			return false;
		else{
			list=hotelStrategyDataHelper.getAll();
			return true;
		}
	}

	@Override
	public boolean modifiedHotelStrategy(HotelStrategyPO strategy) throws RemoteException {
		int i=hotelStrategyDataHelper.update(strategy);
		if(i==0)
			return false;
		else{
			list=hotelStrategyDataHelper.getAll();
			return true;
		}
	}


}
