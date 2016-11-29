package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.OrderPO;

public interface OrderDataServ extends Remote {
    public boolean insertOrder(OrderPO po) throws RemoteException;

    public boolean modifiedOrder(OrderPO po) throws RemoteException;

    public OrderPO getOrder(String OrderID) throws RemoteException;

    public ArrayList<OrderPO> getOrders(String id) throws RemoteException;

    public ArrayList<OrderPO> getAllOrders() throws RemoteException;

}
