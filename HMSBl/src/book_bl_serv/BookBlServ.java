package book_bl_serv;

import VO.BookResult;
import VO.OrderVO;
import VO.RoomAndNum;
import book_bl_servImpl.BookBlServImpl;

import java.util.ArrayList;
import java.util.Iterator;

public interface BookBlServ {
	public static BookBlServ getInstance() {
		return new BookBlServImpl();
	}

	/**
	 * 由客户端调用，用于生成一个订单
	 * @param orderVO 需要生成的订单
	 * @return 返回是否成功
     */
	public boolean produceOrder(OrderVO orderVO);

	public BookResult book(String hotelName, String userId, ArrayList<RoomAndNum> roomAndNums, boolean children, int persons, int inTime, int outTime, int execTime);

	/**
	 *
	 * @param hotelName
	 * @param inTime
	 * @param outTime
     * @return
     */
	public Iterator<RoomAndNum> getNumAvailable(String hotelName, int inTime, int outTime);
}
