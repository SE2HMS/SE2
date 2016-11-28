package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.HotelStrategyPO;

public interface hotelStrategyDataImpl extends Remote{
	
	public void insertStrategy(HotelStrategyPO strategyPO) throws RemoteException;
	
	public HotelStrategyPO[] getStrategyList () throws RemoteException;
	
	public void deleteStrategy(String id) throws RemoteException;
	
	public void modifiedStrategy(HotelStrategyPO strategy) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
	
}
