package order_data_serv;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.OrderPO;

public interface normalOrderDataServ extends Remote{
	
	public OrderPO[] getOrderList(String id) throws RemoteException;
	
	public OrderPO[] getOrderList() throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;

}