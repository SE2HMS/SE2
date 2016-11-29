package hotel_bl_serv;

import VO.HotelVO;
import VO.StrategyVO;
import hotel_bl_servlmpl.HotelBlServImpl;
import strategy_bl_serv.HotelStrategyBlServ;

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
