package book_bl_serv;

import VO.BookResult;
import VO.OrderVO;
import VO.RoomAndNum;
import book_bl_servImpl.BookBlServImpl;

import java.util.ArrayList;
import java.util.Iterator;

public interface BookBlServ {

	/**
	 * 此方法用来得到这个接口的一个实例
	 * 当你需要适用这个接口的时候
	 * 就要通过它的实例来完成
	 * 不需要自己手动建立对象因为你可能不知道它的名字
	 * @return
     */
	public static BookBlServ getInstance() {
		return new BookBlServImpl();
	}

	/**
	 * 由客户端调用，用于生成一个订单
	 * 这个订单似乎现在客户端用不到了，改成下面的booK了
	 * 正在考虑把它改为私有方法，因为这东西确实很难用
	 * 而且这个接口显然返回不了id
	 * @param orderVO 需要生成的订单
	 * @return 返回是否成功
     */
	public boolean produceOrder(OrderVO orderVO);

	/**
	 * 预定方法，生成订单，参数如下：
	 * 这个方法会改动可用房间数量
	 * 会参考信用值
	 * @param hotelName 酒店名
	 * @param userId 用户id
	 * @param roomAndNums 房间以及数量的表
	 * @param children 有无儿童
	 * @param persons 入住人数
	 * @param inTime 入住时间，1，2，3分别表示今天，明天，后天
	 * @param outTime 退房时间，1，2，3分别表示明天，后天，大后天
     * @param execTime 执行时间，0，1分别表示入住当天的18点和22点
     * @return 返回预定结果，成功的时候会包含id，不成功的时候会有错误类型，以及错误信息
     */
	public BookResult book(String hotelName, String userId, ArrayList<RoomAndNum> roomAndNums, boolean children, int persons, int inTime, int outTime, int execTime);

	/**
	 * 得到一段时间内，某个酒店各种房间最多可订数量，也就是这几天中房间数量最少的那天的数量
	 * @param hotelName 酒店名
	 * @param inTime 入住时间，1，2分别表示今天，明天，后天
	 * @param outTime 退房时间，1，2，3分别表示明天，后天，大后天（当前的时间取决于服务器）
     * @return 返回各个房间和数量打包成的类的迭代器
     */
	public Iterator<RoomAndNum> getNumAvailable(String hotelName, int inTime, int outTime);
}
