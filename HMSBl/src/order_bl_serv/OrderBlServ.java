package order_bl_serv;

import VO.OrderState;
import VO.OrderVO;
import com.sun.org.apache.xpath.internal.operations.Or;
import order_bl_servlmpl.OrderBlServImpl;

import java.util.Iterator;

public interface OrderBlServ {

	public Iterator<OrderVO> getAllOrderList();

	public Iterator<OrderVO> getAllNotInOrderList();
	public Iterator<OrderVO> getAllAbnormalOrderList();
	public Iterator<OrderVO> getAllRevokeOrderList();
	public Iterator<OrderVO> getAllFinishOrderList();

	public static OrderBlServ getInstance() {
		return new OrderBlServImpl();
	}

	/**
	 * 得到一个订单
	 * @param id
	 * @return
     */
	public OrderVO getOrderInfo(String id);

	public boolean modifyOrderState(String orderId, OrderState state);

	public Iterator<OrderVO> getNotInOrderList(String userId);

	public Iterator<OrderVO> getAbnormalOrderList(String userId);

	public Iterator<OrderVO> getRevokeOrderList(String userId);

	public Iterator<OrderVO> getFinishOrderList(String userId);

	/**
	 * 得到一个用户所有的订单
	 * @param userId
	 * @return
     */
	public Iterator<OrderVO> getOrderList(String userId);

	public boolean revokeOrder(String orderId);

	public OrderVO getLatestOrder(String userId);
}
