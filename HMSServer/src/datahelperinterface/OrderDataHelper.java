package datahelperinterface;

import java.util.ArrayList;

import PO.OrderPO;

public interface OrderDataHelper {
	public ArrayList<OrderPO> getAll();
	
	public int update(OrderPO o);
	
	public int insert(OrderPO o);
	
	public int delete(String id);
}
