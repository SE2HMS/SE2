package room_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.RoomDataServ;
import PO.RoomPO;
import datahelper.DataFactorylmpl;
import datahelperinterface.DataFactory;
import datahelperinterface.RoomDataHelper;

public class RoomDataServlmpl implements RoomDataServ{
	private ArrayList<RoomPO> list;
	private DataFactory dataFactory;
	private RoomDataHelper roomDataHelper;
	private static RoomDataServlmpl roomDataServlmpl;
	
	public static RoomDataServlmpl getInstance(){
		if(roomDataServlmpl==null)
			roomDataServlmpl=new RoomDataServlmpl();
		return roomDataServlmpl;
	}
	
	private RoomDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			roomDataHelper=dataFactory.getRoomDataHelper();
			list=roomDataHelper.getAll();
		}
	}
	
	

	@Override
	public RoomPO getRoom(String hotelname, String type) throws RemoteException {
		for(int i=0;i<list.size();i++){
			if(list.get(i).getHn().equals(hotelname)
					&&list.get(i).getType().equals(type))
				return list.get(i);
		}
		return null;
	}

	@Override
	public ArrayList<RoomPO> getRoomList(String hotelname) throws RemoteException {
		ArrayList<RoomPO> res=new ArrayList<RoomPO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getHn().equals(hotelname))
				res.add(list.get(i));
		}
		return res;
	}

	@Override
	public boolean insertRoom(RoomPO room) throws RemoteException {
		int i=roomDataHelper.insert(room);
		if(i==0)
			return false;
		else{
			list=roomDataHelper.getAll();
			return true;
		}
	}

	@Override
	public boolean deleteRoom(RoomPO room) throws RemoteException {
		int i=roomDataHelper.delete(room);
		if(i==0)
			return false;
		else{
			list=roomDataHelper.getAll();
			return true;
		}
	}

	@Override
	public boolean modifiedRoom(RoomPO room) throws RemoteException {
		int i=roomDataHelper.update(room);
		if(i==0)
			return false;
		else{
			list=roomDataHelper.getAll();
			return true;
		}
	}
}
