package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.CreditPO;

public interface CreditDataServ extends Remote{
	
	public double getTotel(String id) throws RemoteException;
	
	public CreditPO getDetial(String id) throws RemoteException;
	
	public void insertCredit(CreditPO credit) throws RemoteException;
	
	public void modifiedCredit(CreditPO credit) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
}
