package book_bl_serv;

import PO.*;
import VO.HotelInOrder;
import VO.OrderVO;
import VO.UserInOrder;
import book_bl_servImpl.BookBlServImpl;

public interface BookBlServ {
	public static BookBlServ getInstance() {
		return new BookBlServImpl();
	}

	public void produceOrder(OrderVO orderVO);
}
