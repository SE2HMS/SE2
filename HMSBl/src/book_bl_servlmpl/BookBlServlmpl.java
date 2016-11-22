package book_bl_servlmpl;

import book_bl_serv.BookBlServ;
import rmi.*;
import PO.*;

public class BookBlServlmpl implements BookBlServ {

	@Override
	public void produceOrder(UserPO usera, RoomPO rooma) {
		// TODO Auto-generated method stub
		String id, user, hotel, room, type;
		int num;
		id = null;
		user = null;
		hotel = null;
		room = null;
		type = "normal";
		num = 0;
		OrderPO orderPO = new OrderPO("","","","","","","","");
		try {
			RemoteHelper.getInstance().getOrderDataServ().insertOrder(orderPO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
