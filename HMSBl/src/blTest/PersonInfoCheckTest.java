package blTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import PO.CreditPO;
import PO.HotelPO;
import PO.UserPO;
import credit_bl_servlpml.CreditBlServImpl;
import hotel_bl_servlmpl.HotelBlServImpl;
import login_bl_servlmpl.LoginBlServImpl;

public class PersonInfoCheckTest {
private UserPO user;
private CreditPO credit;
private HotelPO hotel;
private LoginBlServImpl loginServ;
private HotelBlServImpl hotelServ;
private CreditBlServImpl creditServ;

	@Before
	private void setup(){
		ArrayList list=new ArrayList();
		list.add(1);
		list.add(-2);
//		hotel=new HotelPO("nanj","n","h",list);
//		user=new UserPO("123456","123456");
//		user.addHote(hotel.getName());
		loginServ=new LoginBlServImpl();
//		hotelServ=new HotelBlServImpl();
		creditServ=new CreditBlServImpl();
//		credit=new CreditPO(user.getID(),list,-1);
	}

	@Test
	public void test1() {
		assertEquals(loginServ.getUserInfo(user.getID()),user);
	}
	
	@Test
	public void test2(){
//		assertEquals(creditServ.getCreditInfo(user.getID()),credit);
	}
	
	@Test
	public void test3(){
//		assertEquals(hotelServ.getHotelList(user.getID()),hotel);
	}

}
