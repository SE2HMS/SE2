package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.RoomPO;

public interface RoomDataServ extends Remote{
	public RoomPO getRoom(String hotelname,String type) throws RemoteException;
	
	public RoomPO[] getRoomList(String hotelname) throws RemoteException;
	
	public void insertRoom(RoomPO room) throws RemoteException;
	
	public void deleteRoom(String hotelname,String type) throws RemoteException;
	
	public void modifiedRoom (RoomPO room) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
}
