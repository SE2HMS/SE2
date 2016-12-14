package room_bl_servImpl;

import PO.RoomPO;
import VO.*;
import helper.ParseHelper;
import rmi.RemoteHelper;
import room_bl_serv.RoomBlServ;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 12.3妫�鏌�
 */
public class RoomBlServImpl implements RoomBlServ{

	@Override
	public RoomVO getRoomInfo(String hotelName, String type) {
		RoomPO roomPO = null;
		try{
			roomPO = RemoteHelper.getInstance().getRoomDataServ().getRoom(hotelName,type);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		if(roomPO == null) {
			return null;
		}
		RoomVO roomVO = ParseHelper.toRoomVO(roomPO);
		return roomVO;
	}

	@Override
	public boolean addRoom(String hotelName, String type,int total,double price) {
		if(hotelName == null|| type == null) {
			return false;
		}
		boolean success = false;
		try {
			RoomPO roomPO = new RoomPO(hotelName,type,new int[]{total,total,total},total,0,price);
			success = RemoteHelper.getInstance().getRoomDataServ().insertRoom(roomPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean deleteRoom(String hotelName, String type) {
		if(hotelName == null || type == null) {
			return false;
		}
		boolean success = false;
		try {
			RoomPO roomPO = RemoteHelper.getInstance().getRoomDataServ().getRoom(hotelName,type);
			if(roomPO == null) {
				return false;
			}
			success = RemoteHelper.getInstance().getRoomDataServ().deleteRoom(hotelName,type);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean changeRoomNum(String hotelName, ArrayList<String> type, ArrayList<Integer> num, int inTime, int outTime) {
		if(inTime == 0||outTime == 0 || hotelName == null || type == null) {
			return false;
		}
		boolean success = true;
		try {
			for(int i = 0;i<type.size();i++) {
				String aType = type.get(i);
				int aNum = num.get(i);
				RoomPO roomPO = RemoteHelper.getInstance().getRoomDataServ().getRoom(hotelName, aType);
				int avail[] = roomPO.getNum();
				for (int j = inTime; j < outTime; j++) {
					avail[i] += aNum;
				}
				roomPO.setNum(avail);
				success = success && RemoteHelper.getInstance().getRoomDataServ().modifiedRoom(roomPO);
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean offlineOrder(String hotelName, String type, int num) {
		boolean success = false;
		try {
			RoomPO roomPO = RemoteHelper.getInstance().getRoomDataServ().getRoom(hotelName,type);
			roomPO.setOfflineOrdered(roomPO.getOfflineOrdered() + num);
			success = RemoteHelper.getInstance().getRoomDataServ().modifiedRoom(roomPO);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean changeRoomNum(OrderVO orderVO) {
		if(orderVO == null) {
			return false;
		}
		if(orderVO.getState().equals(OrderState.FINISH)) {
			return false;
		}
		String hotelName = orderVO.getHotel().getHotelName();
		ArrayList<RoomInOrder> rooms = orderVO.getHotel().getRooms();
		ArrayList<String> types = new ArrayList<>();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 0;i<rooms.size();i++) {
			RoomInOrder roomInOrder = rooms.get(i);
			types.add(roomInOrder.getType());
			nums.add(roomInOrder.getNum());
		}
		long millis = Calendar.getInstance().getTimeInMillis();
		Date inDate = orderVO.getInTime();
		Date outDate = orderVO.getOutTime();
		int inTime = (int)(inDate.getTime() % 86400 - millis % 86400);
		int outTime = (int)(outDate.getTime() % 86400 - millis % 86400);
		return this.changeRoomNum(hotelName,types,nums,inTime,outTime);
	}
}
