package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.WebStrategyPO;

public interface WebStrategyDataImpl extends Remote{
	
	public void insertStrategy(WebStrategyPO strategyPO) throws RemoteException;
	
	public WebStrategyPO[] getStrategyList () throws RemoteException;
	
	public void deleteStrategy(String id) throws RemoteException;
	
	public void modifiedStrategy(WebStrategyPO strategy) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
}
