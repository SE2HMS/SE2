package room_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	private static int updateDate;
	
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
		updateDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		new Thread(() -> {
			Calendar calendar = Calendar.getInstance();
			if(calendar.get(Calendar.HOUR_OF_DAY) < 1 && calendar.get(Calendar.MINUTE) < 1) {
				if(calendar.get(Calendar.DAY_OF_MONTH) != updateDate) {
					updateDate = calendar.get(Calendar.DAY_OF_MONTH);
					for(RoomPO roomPO:list) {
						int[] avail = roomPO.getNum();
						avail[0] = avail[1];
						avail[1] = avail[2];
						avail[3] = roomPO.getTotel() - roomPO.getOfflineOrdered();
						roomPO.setNum(avail);
						roomDataHelper.update(roomPO);
					}
				}
			}
			try {
				Thread.sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Override
	public ArrayList<RoomPO> getRoomByType(String hotelname,String type) throws RemoteException {
		ArrayList<RoomPO> roomPOs = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getHn().equals(hotelname)
				&&list.get(i).getType().equals(type)) {
				roomPOs.add(list.get(i));
			}
		}
		return roomPOs;
	}

	@Override
	public RoomPO getRoom(String hotelname, String name) throws RemoteException {
		for(int i=0;i<list.size();i++){
			if(list.get(i).getHn().equals(hotelname)
					&&list.get(i).getName().equals(name))
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
