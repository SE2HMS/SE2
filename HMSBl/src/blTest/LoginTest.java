package blTest;

import org.junit.Before;
import org.junit.Test;

import PO.UserPO;
import login_bl_servlmpl.LoginBlServImpl;

public class LoginTest {
	private String id, password;
	private UserPO user1,user2;
	private LoginBlServImpl serv;
	@Before
	public void setup(){
		id="123456";
		password="123456";
//		user1=new UserPO("123456","123456");
//		user2=new UserPO("654321","123456");
		serv=new LoginBlServImpl();
	}

	@Test
	public void test1() {
//		assertTrue(!serv.isCorrect(id, ""));
//		assertTrue(!serv.isCorrect(id, "12345"));
//		assertTrue(serv.isCorrect(id, password));
	}
	
	@Test
	public void test2(){
//		assertTrue(!serv.register(user1));
//		assertTrue(serv.register(user2));
	}

}
