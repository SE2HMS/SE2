package room_bl_serv;

import VO.RoomVO;

public interface RoomBlServ {
	/**
	 * 得到某种类型的房间具体的信息
	 * @param hotelName 房间名
	 * @param type 房间类型
     * @return
     */
	public RoomVO getRoomInfo(String hotelName, String type);
}
