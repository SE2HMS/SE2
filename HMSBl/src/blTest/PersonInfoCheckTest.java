package blTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Login_bl_servlmpl.LoginBlServlmpl;
import PO.CreditPO;
import PO.HotelPO;
import PO.UserPO;
import credit_bl_servlpml.CreditBlServlmpl;
import hotel_bl_servlmpl.HotelBlServlmpl;

public class PersonInfoCheckTest {
private UserPO user;
private CreditPO credit;
private HotelPO hotel;
private LoginBlServlmpl loginServ;
private HotelBlServlmpl hotelServ;
private CreditBlServlmpl creditServ;

	@Before
	private void setup(){
		ArrayList list=new ArrayList();
		list.add(1);
		list.add(-2);
//		hotel=new HotelPO("nanj","n","h",list);
//		user=new UserPO("123456","123456");
//		user.addHote(hotel.getName());
		loginServ=new LoginBlServlmpl();
		hotelServ=new HotelBlServlmpl();
		creditServ=new CreditBlServlmpl();
//		credit=new CreditPO(user.getID(),list,-1);
	}

	@Test
	public void test1() {
		assertEquals(loginServ.getUserInfo(user.getID()),user);
	}
	
	@Test
	public void test2(){
		assertEquals(creditServ.getCreditInfo(user.getID()),credit);
	}
	
	@Test
	public void test3(){
		assertEquals(hotelServ.getHotelList(user.getID()),hotel);
	}

}
