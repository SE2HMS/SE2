package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.HotelPO;

public interface HotalDataServ extends Remote {
	
	public void insertHotel(HotelPO hotel) throws RemoteException;
	
	public HotelPO getHotel (String name) throws RemoteException;
	
	public void deleteHotel (String name) throws RemoteException;
	
	public void modifiedHotel (HotelPO hotel) throws RemoteException;
	
	public HotelPO[] getHotelList (String businesscircle) throws RemoteException;
}
