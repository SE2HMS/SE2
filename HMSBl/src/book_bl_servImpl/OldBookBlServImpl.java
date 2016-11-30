package book_bl_servImpl;

import VO.OrderVO;
import book_bl_serv.BookBlServ;
import rmi.*;
import PO.*;

import java.util.Random;

/**
 * @deprecated
 * 改了一下，id不生成了
 */
public class OldBookBlServImpl implements BookBlServ {

	@Override
	/**
	 * 这个方法由客户端调用用于生成一个订单
	 */
	public boolean produceOrder(OrderVO order) {
		String userId = null;
		String userName = order.getUser().getName();
		String contact = order.getUser().getContact();
		String hotel = order.getHotel().getHotelName();
		String type = order.getState().toString();
		String inTime = order.getInTime().toString();
		String outTime = order.getOutTime().toString();
		String execTime = order.getExecTime().toString();
		double total = (double)order.getTotal();
		boolean success = false;
		try {
			String id = generateId();
			while(RemoteHelper.getInstance().getOrderDataServ().getOrder(id) != null) {
				id = generateId();
			}
			OrderPO orderPO = new OrderPO(id,userId,hotel,userName,contact,type,inTime,outTime,execTime,total);
			success = RemoteHelper.getInstance().getOrderDataServ().insertOrder(orderPO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * @deprecated 这个方法已经木有用了
	 * 生成一个随机长度为16的id
	 * @return
     */
	private String generateId() {
		String result = "";
		final int length = 16;
		for (int i = 0; i < length; i++) {
			result += Math.abs(new Random().nextInt() % 10);
		}
		return result;
	}
}
