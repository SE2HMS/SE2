package datahelperinterface;

import java.util.ArrayList;

import datahelper.SubOrder;

public interface SubOrderDataServ {
	public ArrayList<SubOrder> getSubOrders(String orderid);
	
	public boolean insertSubOrder(SubOrder s);
}
