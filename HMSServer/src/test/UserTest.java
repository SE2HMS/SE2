package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.UserDataServ;
import PO.UserPO;
import user_data_servlmpl.UserDataServlmpl;

public class UserTest {
	private UserDataServ userDataServ;
	private UserPO u1,u2;
	
	@Before
	public void setup(){
		userDataServ=UserDataServlmpl.getInstance();
		u1=new UserPO(null,"1","11","wanger","97/02/23",0,0,0,"person");
		u2=new UserPO(null,"2","22","zhangsan","nju",0,0,0,"company");
	}

	@Test
	public void test1() throws RemoteException {
		assertTrue(userDataServ.insertUser(u1));
	}
	
	@Test 
	public void test2() throws RemoteException{
		u1=userDataServ.getUser("1");
		u1.setContactInfo("111");
		userDataServ.modifiedUser(u1);
		assertEquals(userDataServ.getUser("1").getContactInfo(),"111");
	}
	
	@Test
	public void test3() throws RemoteException{
		assertTrue(userDataServ.deleteUser("1"));
	}
	
	@Test
	public void test4() throws RemoteException{
		userDataServ.insertUser(u1);
		userDataServ.insertUser(u2);
		ArrayList<UserPO> list=userDataServ.getUserList();
		boolean tag=false;
		tag=list.get(0).getName().equals("wanger");
		tag=list.get(1).getName().equals("zhangsan");
		tag=list.size()==2;
		assertTrue(tag);
	}

}
