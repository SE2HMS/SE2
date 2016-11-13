package room_data_servlmpl;

import java.rmi.RemoteException;

import PO.RoomPO;
import room_data_serv.RoomDataServ;

public class RoomDataServlpml implements RoomDataServ{

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

	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void finish() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
