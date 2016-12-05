package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.HotelDataServ;
import PO.HotelPO;
import hotel_data_servlmpl.HotelDataServlmpl;

public class HotelTest {
	private HotelDataServ hotelDataServ;
	private HotelPO h1,h2,h3;
	
	@Before
	public void setup(){
		hotelDataServ=HotelDataServlmpl.getInstance();
		h1=new HotelPO("jinling","xinjiekou","haha","23hao",4);
		h2=new HotelPO("tongling","xinjiekou","xixi","25hao",2);
		h3=new HotelPO("yinling","xinjieko","hehe","24hao",3);
	}

	@Test
	public void test1() throws RemoteException {
		assertTrue(hotelDataServ.insertHotel(h1));
	}
	
	@Test
	public void test2() throws RemoteException{
		assertEquals(hotelDataServ.getHotel("jinling").getAddress(),"23hao");
	}
	
	@Test
	public void test3() throws RemoteException{
		h1.setAddress("26hao");
		hotelDataServ.modifiedHotel(h1);
		assertEquals(hotelDataServ.getHotel("jinling").getAddress(),"26hao");
	}
	
	@Test
	public void test4() throws RemoteException{
		hotelDataServ.insertHotel(h2);
		hotelDataServ.insertHotel(h3);
		ArrayList<HotelPO> list=hotelDataServ.getHotelList("xinjiekou");
		boolean tag=false;
		tag=list.get(0).getName().equals(h1.getName());
		tag=list.get(1).getName().equals(h2.getName());
		tag=list.size()==2;
		assertTrue(tag);
	}

}
