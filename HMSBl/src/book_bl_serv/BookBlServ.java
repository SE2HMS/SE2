package book_bl_serv;

import PO.*;
import VO.HotelInOrder;
import VO.UserInOrder;

public interface BookBlServ {
	public void produceOrder(UserInOrder user, HotelInOrder hotel);
}
