package order_bl_serv;

import VO.OrderVO;

import java.util.Iterator;

public interface OrderBlServ {

	/**
	 * 得到一个订单
	 * @param id
	 * @return
     */
	public OrderVO getOrderInfo(String id);

	/**
	 * 得到一个用户所有的订单
	 * @param userId
	 * @return
     */
	public Iterator<OrderVO> getOrderList(String userId);
}
