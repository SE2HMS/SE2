package order_bl_serv;

import PO.OrderPO;

public interface OrderBlServ {

	/**
	 *
	 * @param id
	 * @return
     */
	public OrderPO getOrderInfo(String id);
}
