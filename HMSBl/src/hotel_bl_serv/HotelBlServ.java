package hotel_bl_serv;

import VO.HotelVO;
import VO.OrderVO;
import VO.StrategyVO;
import hotel_bl_servlmpl.HotelBlServImpl;

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

	public boolean addHotel(String hotelName,int businessCIrcle,int location,String intro,double star);

	public Iterator<OrderVO> getOrderInHotel(String userId,String hotelName);

//	public Iterator<HotelVO> search(String name,double starLevel,double commentLevel, String businessCircle);

	/**
	 * 这个是搜索方法
	 * @param location 位置是大于一的，代表什么就查表吧，表先缓一缓
	 * @param businesscircle 商圈同上
	 * @param name 酒店名，只要含有就返回
	 * @param haveOrdered 是否预定过
	 * @param roomType 房间类型也查表吧
	 * @param price
	 * @param roomNum 房间数量
	 * @param inTime 入住时间
	 * @param outTime 退房时间
	 * @param starLevel 星级
     * @param commentLevel
     * @return 返回符合条件的酒店的迭代器
     */
	public Iterator<HotelVO> search(String userId,int location, int businesscircle, String name, boolean haveOrdered,int roomType, int price, int roomNum, int inTime, int outTime, int starLevel, int commentLevel);

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
