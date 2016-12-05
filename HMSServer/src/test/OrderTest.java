package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.OrderDataServ;
import PO.OrderPO;
import order_data_servlmpl.OrderDataServlmpl;

public class OrderTest {
	private OrderDataServ orderDataServ;
	private OrderPO o1,o2,o3;
	
	@Before
	public void setup(){
		orderDataServ=OrderDataServlmpl.getIntanse();
		o1=new OrderPO(null,"1","jinling","wanger","11","in","16/11/30","16/12/01","16/11/30/20/30",240);
		o2=new OrderPO(null,"1","jinling","wanger","11","out","16/11/29","16/11/30","16/11/29/20/30",1200);
		o3=new OrderPO(null,"2","yinling","zhangsan","22","in","16/11/30","16/12/01","16/11/30/20/30",240);
	}
	@Test
	public void test1() throws RemoteException {
		assertTrue(orderDataServ.insertOrder(o1));
	}
	
	@Test
	public void test2() throws RemoteException{
		assertEquals(orderDataServ.getOrder("1").getRoom().get(0),"a");
	}
	
	@Test
	public void test3() throws RemoteException{
		o1=orderDataServ.getOrder("1");
		o1.setType("out");
		orderDataServ.modifiedOrder(o1);
		assertEquals(orderDataServ.getOrder("1").getType(),"out");
	}
	
	@Test
	public void test4() throws RemoteException{
		orderDataServ.insertOrder(o2);
		orderDataServ.insertOrder(o3);
		ArrayList<OrderPO> list=orderDataServ.getOrders("1");
		boolean tag=false;
		tag=list.get(0).getID().equals("1");
		tag=list.get(1).getID().equals("2");
		tag=list.size()==2;
	}
	
	@Test 
	public void test5() throws RemoteException{
		ArrayList<OrderPO> list=orderDataServ.getAllOrders();
		assertTrue(list.size()==3);
	}

}
