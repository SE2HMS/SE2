package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.CreditDataServ;
import PO.CreditPO;
import credit_data_servlmpl.CreditDataServlmpl;

public class CreditTest {
	private CreditDataServ creditDataServ;
	private CreditPO c1,c2,c3;
	
	@Before
	public void setup(){
		creditDataServ=CreditDataServlmpl.getInstance();
		c1=new CreditPO(null,"16/11/29","1",4,2.1,"order");
		c2=new CreditPO(null,"16/11/29","1",2.95,-1.05,"revoke");
		c3=new CreditPO(null,"16/11/29","2",5,5,"order");
	}

	@Test
	public void test1() throws RemoteException {
		assertTrue(creditDataServ.insertCredit(c1));
	}
	
	@Test
	public void test2() throws RemoteException{
		creditDataServ.insertCredit(c2);
		creditDataServ.insertCredit(c3);
		boolean tag=false;
		ArrayList<CreditPO> list=creditDataServ.getDetial("1");
		tag=list.get(0).getBehavior().equals("order");
		System.out.println(c1.getID());
		tag=list.get(1).getBehavior().equals("revoke");
		assertTrue(tag);
	}

}
