package hotel_bl_serv;

import VO.HotelVO;

public interface HotelBlServ {
	public HotelVO getHotelInfo(String name);
	public boolean modifyHotelInfo(HotelVO hotel);
	public HotelVO[] getHotelList(String id);
	public HotelVO[] getAllHotel();
}
