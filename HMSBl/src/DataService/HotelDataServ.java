package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.HotelPO;

public interface HotelDataServ extends Remote {
	
	public boolean insertHotel(HotelPO hotel) throws RemoteException;
	
	public HotelPO getHotel (String name) throws RemoteException;
	
	public boolean modifiedHotel (HotelPO hotel) throws RemoteException;
	
	public ArrayList<HotelPO> getHotelList (String businesscircle) throws RemoteException;

	public ArrayList<HotelPO> getAllHotel() throws RemoteException;

}
