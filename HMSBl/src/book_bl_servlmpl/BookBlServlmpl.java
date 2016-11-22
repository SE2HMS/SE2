package book_bl_servlmpl;

import VO.OrderVO;
import book_bl_serv.BookBlServ;
import rmi.*;
import PO.*;

import java.util.Random;

public class BookBlServlmpl implements BookBlServ {

	@Override
	/**
	 * 这个方法由客户端调用用于生成一个订单
	 */
	public void produceOrder(OrderVO order) {
		String userId = order.getUser().getId();
		String userName = order.getUser().getName();
		String contact = order.getUser().getContact();
		String hotel = order.getHotel().getHotelName();
		String type = order.getState().toString();
		String inTime = order.getInTime().toString();
		String outTime = order.getOutTime().toString();
		String execTime = order.getExecTime().toString();
		try {
			String id = generateId();
			while(RemoteHelper.getInstance().getOrderDataServ().getOrder(id) != null) {
				id = generateId();
			}
			OrderPO orderPO = new OrderPO(id,userId,hotel,userName,contact,type,outTime,execTime);
			RemoteHelper.getInstance().getOrderDataServ().insertOrder(orderPO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String generateId() {
		String result = "";
		final int length = 16;
		for (int i = 0; i < length; i++) {
			result += Math.abs(new Random().nextInt() % 10);
		}
		return result;
	}
}
