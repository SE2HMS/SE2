package room_bl_serv;

import VO.RoomVO;
import room_bl_servImpl.RoomBlServImpl;

import java.util.ArrayList;

public interface RoomBlServ {
	public static RoomBlServ getInstance() {
		return new RoomBlServImpl();
	}

	/**
	 * 得到某种类型的房间具体的信息
	 * @param hotelName 房间名
	 * @param type 房间类型
     * @return
     */
	public RoomVO getRoomInfo(String hotelName, String type);

	public boolean addRoom(String hotelName, String type,int total,double price);

	public boolean deleteRoom(String hotelName, String type);

	/**
	 * 这个是线上预定的用
	 * 入住了减一（传-1）
	 * 退房了加一
	 * 撤销了也加
	 * inTime和outTime不要传0进来= =
	 * @param hotelName
	 * @param type
	 * @param num
	 * @param inTime
	 * @param outTime
     * @return
     */
	public boolean changeRoomNum(String hotelName, ArrayList<String> type, ArrayList<Integer> num, int inTime, int outTime);

	/**
	 * 这个是用来手动改的时候调用的
	 * （线下有人入住的时候用这个）
	 * num可以是负的（入住的时候传负数，退房的时候传正数）
	 * @param hotelName
	 * @param type
	 * @param num
     * @return
     */
	public boolean offlineOrder(String hotelName,String type,int num);
}
