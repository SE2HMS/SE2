package hotel_bl_serv;

import VO.HotelVO;
import VO.StrategyVO;
import hotel_bl_servlmpl.HotelBlServImpl;

import java.util.Date;
import java.util.Iterator;

public interface HotelBlServ {

	public static HotelBlServ getInstance() {
		return new HotelBlServImpl();
	}

	/**
	 * 得到某个酒店的信息
	 * @param name
	 * @return
     */
	public HotelVO getHotelInfo(String name);

	public Iterator<HotelVO> search(String name,double starLevel,double commentLevel, String businessCircle);

	public Iterator<HotelVO> search(String location, String businesscircle, String name, boolean haveOrdered,String roomType, double minPrice, double maxPrice, int roomNum, Date inTime, Date outTime, int starLevel, double minComment, double maxComment);

	/**
	 * 修改酒店信息，意思很明显。。
	 * @param hotel
	 * @return
     */
	public boolean modifyHotelInfo(HotelVO hotel);

	/**
	 * 得到所有的策略
	 * @param hotelName
	 * @return
     */
	public Iterator<StrategyVO> getStrategyList(String hotelName);

	/**
	 * 得到所有酒店的信息
	 * @return
     */
	public Iterator<HotelVO> getAllHotel(String businessCircle);
}
