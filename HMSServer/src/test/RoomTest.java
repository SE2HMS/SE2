package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DataService.RoomDataServ;
import PO.RoomPO;
import room_data_servlmpl.RoomDataServlmpl;

public class RoomTest {
	private RoomDataServ roomDataServ;
	private RoomPO r1,r2,r3;
	
	@Before
	public void setup(){
		roomDataServ=RoomDataServlmpl.getInstance();
		int a[]={3,3,3,};
		r1=new RoomPO("jinling","a",a,3,0,120);
		r2=new RoomPO("jinling","b",a,3,0,60);
		r3=new RoomPO("yinling","a",a,3,0,240);
	}
	
	@Test
	public void test1() throws RemoteException {
		assertTrue(roomDataServ.insertRoom(r1));
	}
	
	@Test
	public void test2() throws RemoteException{
		int a[]={3,2,3};
		r1.setNum(a);
		roomDataServ.modifiedRoom(r1);
		assertEquals(roomDataServ.getRoom("jinling", "a").getNum()[1],2);
	}
	
	@Test
	public void test3() throws RemoteException{
		assertTrue(roomDataServ.deleteRoom(r1));
	}
	
	@Test
	public void test4() throws RemoteException{
		roomDataServ.insertRoom(r1);
		roomDataServ.insertRoom(r2);
		roomDataServ.insertRoom(r3);
		ArrayList<RoomPO> list=roomDataServ.getRoomList("jinling");
		boolean tag=false;
		tag=list.get(0).getType().equals("a");
		tag=list.get(1).getType().equals("b");
		tag=list.size()==2;
		assertTrue(tag);
	}

}
