package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datahelper.SubOrder;
import datahelper.SubOrderDataServlmpl;
import datahelperinterface.SubOrderDataServ;

public class SubOrderTest {
	private SubOrderDataServ subOrderDataServ;
	private SubOrder s1,s2,s3;
	
	@Before
	public void setup(){
		subOrderDataServ=SubOrderDataServlmpl.getInstance();
		s1=new SubOrder("1","a",1,120,120);
		s2=new SubOrder("1","b",2,60,120);
		s3=new SubOrder("2","a",10,120,1200);
	}
	
	
	@Test
	public void test1() {
		assertTrue(subOrderDataServ.insertSubOrder(s1));
	}
	
	@Test
	public void test2(){
		subOrderDataServ.insertSubOrder(s2);
		subOrderDataServ.insertSubOrder(s3);
		ArrayList<SubOrder> list=subOrderDataServ.getSubOrders("1");
		boolean tag=false;
		tag=list.get(0).getRoomType().equals("a");
		tag=list.get(1).getRoomType().equals("b");
		tag=list.size()==2;
		assertTrue(tag);
	}

}
