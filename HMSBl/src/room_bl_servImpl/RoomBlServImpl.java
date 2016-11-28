package room_bl_servImpl;

import PO.RoomPO;
import VO.RoomVO;
import rmi.RemoteHelper;
import room_bl_serv.RoomBlServ;

import java.rmi.RemoteException;

/**
 * 完工
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
		RoomVO roomVO = parseRoomVO(roomPO);
		return null;
	}

	/**
	 * 把RoomPO变成RoomVO
	 * @param roomPO
	 * @return
     */
	private RoomVO parseRoomVO(RoomPO roomPO) {
		if(roomPO == null) {
			return null;
		}
		String type = roomPO.getType();
		int price = (int)roomPO.getPrice();
		int total = roomPO.getTotel();
		int[] available = roomPO.getNum();
		RoomVO roomVO = new RoomVO(type,price,total,available);
		return roomVO;
	}

}
