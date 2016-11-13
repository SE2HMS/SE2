package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.OrderPO;

public interface OrderDataServ extends Remote{
	
	public void insertOrder (OrderPO po) throws RemoteException;
	
	public void modifiedOrder(OrderPO po) throws RemoteException;
	
	public OrderPO getOrder(String OrderID) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
}
