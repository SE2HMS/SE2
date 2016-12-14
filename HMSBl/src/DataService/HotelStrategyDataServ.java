package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.HotelStrategyPO;

public interface HotelStrategyDataServ extends Remote{
	
	public boolean insertStrategy(HotelStrategyPO strategyPO) throws RemoteException;
	
	public ArrayList<HotelStrategyPO> getStrategyList (String hotelName) throws RemoteException;
	
	public boolean deleteStrategy(String id) throws RemoteException;
	
	public boolean modifiedStrategy(HotelStrategyPO strategy) throws RemoteException;
	
}
