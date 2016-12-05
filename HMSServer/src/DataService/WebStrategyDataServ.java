package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.WebStrategyPO;

public interface WebStrategyDataServ extends Remote{
	
	public boolean insertWebStrategy(WebStrategyPO strategyPO) throws RemoteException;
	
	public ArrayList<WebStrategyPO> getWebStrategyList(String strategynaem,String bc) throws RemoteException;
	
	public boolean deleteWebStrategy(WebStrategyPO w) throws RemoteException;
	
	public boolean modifiedWebStrategy(WebStrategyPO strategy) throws RemoteException;
	
}
