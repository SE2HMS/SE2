package room_bl_serv;

import PO.RoomPO;
import PO.UserPO;

public interface RoomBlServ {
	public RoomPO getRoomInfo(String hotelName,String type);
}
