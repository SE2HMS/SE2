package order_bl_serv;

import VO.OrderState;
import VO.OrderVO;
import VO.UserOrderAction;
import order_bl_servlmpl.OrderBlServImpl;

import java.util.Iterator;

public interface OrderBlServ {

    /**
     * 得到所有的订单
     *
     * @return 返回所有订单
     */
    public Iterator<OrderVO> getAllOrderList();

    /**
     * 得到所有未执行的订单
     *
     * @return 一个迭代器
     */
    public Iterator<OrderVO> getAllNotInOrderList();

    /**
     * 得到所有异常订单
     *
     * @return 一个迭代器
     */
    public Iterator<OrderVO> getAllAbnormalOrderList();

    /**
     * 得到所有撤销的订单
     *
     * @return 一个迭代器
     */
    public Iterator<OrderVO> getAllRevokeOrderList();

    /**
     * 得到所有已执行的订单
     *
     * @return 一个迭代器
     */
    public Iterator<OrderVO> getAllFinishOrderList();

    /**
     * 得到此接口的一个实例
     *
     * @return 返回一个实例
     */
    public static OrderBlServ getInstance() {
        return new OrderBlServImpl();
    }

    /**
     * 得到一个订单
     * 这个应该没有用了吧
     *
     * @param id 订单的id
     * @return 返回订单信息
     */
    public OrderVO getOrderInfo(String id);

    /**
     * 修改订单的状态
     * 同时要修改房间数量
     * 以及信用信息
     *
     * @param orderId 订单的id
     * @param action  动作
     * @return 返回是否成功
     */
    public boolean modifyOrderState(String orderId, UserOrderAction action);

    /**
     * 得到一个用户的未执行订单列表
     *
     * @param userId 用户id
     * @return 返回该列表的迭代器
     */
    public Iterator<OrderVO> getNotInOrderList(String userId);

    /**
     * 得到一个用户的异常订单列表
     *
     * @param userId 用户id
     * @return 返回该列表的迭代器
     */
    public Iterator<OrderVO> getAbnormalOrderList(String userId);

    /**
     * 得到一个用户的撤销订单列表
     *
     * @param userId 用户id
     * @return 返回该列表的迭代器
     */
    public Iterator<OrderVO> getRevokeOrderList(String userId);

    /**
     * 得到一个用户的已执行订单列表
     *
     * @param userId 用户id
     * @return 返回该列表的迭代器
     */
    public Iterator<OrderVO> getFinishOrderList(String userId);

    /**
     * 得到一个用户所有的订单
     *
     * @param userId 用户的id
     * @return 返回该列表的迭代器
     */
    public Iterator<OrderVO> getOrderList(String userId);

    public Iterator<OrderVO> getHotelOrderList(String hotelName);

    public Iterator<OrderVO> getHotelNotInOrderList(String hotelName);

    public Iterator<OrderVO> getHotelFinishOrderList(String hotelName);

    public Iterator<OrderVO> getHotelAbnomalOrderList(String hotelName);

    public Iterator<OrderVO> getHotelRevokeOrderList(String hotelName);

    public boolean checkIn(String orderId);

    public boolean checkOut(String orderId);

    public boolean delayCheckIn(String orderId);

    public boolean revokeExceptionOrder(String orderId,boolean all);

    /**
     * 撤销订单
     * 如果在最晚执行时间前六个小时内撤销
     * 则扣除信用
     *
     * @param orderId 用户id
     * @return 返回是否成功
     */
    public boolean revokeOrder(String orderId);

    /**
     * 得到一个用户最近的一个订单
     *
     * @param userId 用户id
     * @return 返回该订单
     */
    public OrderVO getLatestOrder(String userId);
}
