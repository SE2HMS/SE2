package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.StrategyPO;

public interface hotelStrategyDataImpl extends Remote{
	
	public void insertStrategy(StrategyPO strategyPO) throws RemoteException;
	
	public StrategyPO[] getStrategyList () throws RemoteException;
	
	public void deleteStrategy(String id) throws RemoteException;
	
	public void modifiedStrategy(StrategyPO strategy) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
	
}
