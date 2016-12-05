package room_bl_servImpl;

import PO.RoomPO;
import VO.RoomVO;
import helper.ParseHelper;
import rmi.RemoteHelper;
import room_bl_serv.RoomBlServ;

import java.rmi.RemoteException;

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

}
