package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import PO.CreditPO;
import PO.HotelPO;
import PO.OrderPO;
import PO.RoomPO;
import PO.StrategyPO;
import PO.UserPO;
import credit_data_serv.CreditDataServ;
import hotel_data_serv.HotalDataServ;
import order_data_serv.OrderDataServ;
import order_data_serv.abnormalOrderDataServ;
import order_data_serv.normalOrderDataServ;
import order_data_serv.revokOrderDataServ;
import room_data_serv.RoomDataServ;
import strategy_data_serv.hotelStrategyDataImpl;
import strategy_data_serv.memberStrategyDataImpl;
import user_data_serv.UserDataServ;

public class DataRemoteObject extends UnicastRemoteObject implements
	CreditDataServ,HotalDataServ,OrderDataServ,normalOrderDataServ,abnormalOrderDataServ,
	revokOrderDataServ,RoomDataServ,hotelStrategyDataImpl,memberStrategyDataImpl,UserDataServ{

	protected DataRemoteObject() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertUser(UserPO user) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserPO getUser(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedUser(UserPO user) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserPO[] getUserList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertStrategy(StrategyPO strategyPO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StrategyPO[] getStrategyList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStrategy(String id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedStrategy(StrategyPO strategy) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoomPO getRoom(String hotelname, String type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomPO[] getRoomList(String hotelname) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRoom(RoomPO room) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoom(String hotelname, String type) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedRoom(RoomPO room) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderPO[] getOrderList(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderPO[] getOrderList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrder(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedOrder(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderPO getOrder(String OrderID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertHotel(HotelPO hotel) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HotelPO getHotel(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHotel(String name) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedHotel(HotelPO hotel) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HotelPO[] getHotelList(String businesscircle) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotel(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CreditPO getDetial(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCredit(CreditPO credit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedCredit(CreditPO credit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
