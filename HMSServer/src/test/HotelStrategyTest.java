package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.HotelStrategyDataServ;
import PO.HotelStrategyPO;
import strategy_data_servlmpl.HotelStrategyDataServlmpl;

public class HotelStrategyTest {
	private HotelStrategyDataServ hotelStrategyDataServ;
	private HotelStrategyPO h1,h2,h3;
	
	@Before 
	public void setup(){
		hotelStrategyDataServ=HotelStrategyDataServlmpl.getInstance();
		h1=new HotelStrategyPO("jinling","double11","11/11",0.9,"date");
		h2=new HotelStrategyPO("jinling","roomnum",null,0.8,"beyond3");
		h3=new HotelStrategyPO("yinling","double11","11/11",0.7,"date");
	}

	@Test
	public void test1() throws RemoteException {
		assertTrue(hotelStrategyDataServ.insertHotelStrategy(h1));
	}
	
	@Test
	public void test2() throws RemoteException{
		h1.setSpecialInfo("11/12");
		h1.setDiscount(0.6);
		hotelStrategyDataServ.modifiedHotelStrategy(h1);
		assertEquals(hotelStrategyDataServ.getHotelStrategyList("jinling").get(0).getDiscount(),0.6,0.0);
	}
	
	@Test
	public void test3() throws RemoteException{
		assertTrue(hotelStrategyDataServ.deleteHotelStrategy(h1));
	}
	
	@Test
	public void test4() throws RemoteException{
		hotelStrategyDataServ.insertHotelStrategy(h1);
		hotelStrategyDataServ.insertHotelStrategy(h2);
		hotelStrategyDataServ.insertHotelStrategy(h3);
		boolean tag=false;
		ArrayList<HotelStrategyPO> list=hotelStrategyDataServ.getHotelStrategyList("jinling");
		tag=list.get(0).getStrategyName().equals("double11");
		tag=list.get(1).getStrategyName().equals("roomnum");
		tag=list.size()==2;
		assertTrue(tag);
	}

}
