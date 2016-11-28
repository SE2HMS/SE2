package book_bl_serv;

import VO.OrderVO;
import book_bl_servImpl.BookBlServImpl;

public interface BookBlServ {
	public static BookBlServ getInstance() {
		return new BookBlServImpl();
	}

	/**
	 * 由客户端调用，用于生成一个订单
	 * @param orderVO 需要生成的订单
     */
	public void produceOrder(OrderVO orderVO);
}
