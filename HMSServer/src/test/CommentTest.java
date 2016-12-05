package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.CommentDataServ;
import PO.CommentPO;
import comment_data_servlmpl.CommentDataServlmpl;

public class CommentTest {
	private CommentDataServ commentDataServ;
	private CommentPO c1,c2,c3;
	
	@Before
	public void setup(){
		commentDataServ=CommentDataServlmpl.getInstance();
		c1=new CommentPO("hotel","good");
		c2=new CommentPO("hotel","bad");
		c3=new CommentPO("hotel1","hehe");
	}
	
	@Test
	public void test1() throws RemoteException {
		assertTrue(commentDataServ.insert(c1));
	}
	
	@Test
	public void test2() throws RemoteException{
		assertEquals(commentDataServ.getComments("hotel").get(0).getDetials(),c1.getDetials());
	}
	
	@Test
	public void test3() throws RemoteException{
		assertTrue(commentDataServ.delete(c1));
	}
	
	@Test
	public void test4() throws RemoteException{
		assertTrue(commentDataServ.getComments("hotel").isEmpty());
	}
	
	@Test
	public void test5() throws RemoteException{
		commentDataServ.insert(c1);
		commentDataServ.insert(c2);
		commentDataServ.insert(c3);
		ArrayList<CommentPO> list=commentDataServ.getComments("hotel");
		assertTrue(list.size()==2);
	}

}
