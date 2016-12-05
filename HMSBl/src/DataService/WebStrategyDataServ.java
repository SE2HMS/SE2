package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.WebStrategyPO;

public interface WebStrategyDataServ extends Remote{
	
	public boolean insertStrategy(WebStrategyPO strategyPO) throws RemoteException;
	
	public ArrayList<WebStrategyPO> getStrategyList () throws RemoteException;
	
	public boolean deleteStrategy(String id) throws RemoteException;
	
	public boolean modifiedStrategy(WebStrategyPO strategy) throws RemoteException;
	
}
