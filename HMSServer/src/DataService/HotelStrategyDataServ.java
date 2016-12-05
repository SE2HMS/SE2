package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.HotelStrategyPO;

public interface HotelStrategyDataServ extends Remote{
	
	public boolean insertHotelStrategy(HotelStrategyPO strategyPO) throws RemoteException;
	
	public ArrayList<HotelStrategyPO> getHotelStrategyList(String hotelname) throws RemoteException;
	
	public boolean deleteHotelStrategy(HotelStrategyPO strategyPO) throws RemoteException;
	
	public boolean modifiedHotelStrategy(HotelStrategyPO strategy) throws RemoteException;
	
}
