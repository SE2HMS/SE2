package datahelperinterface;

import java.util.ArrayList;

import PO.HotelStrategyPO;

public interface HotelStrategyDataHelper {
	public ArrayList<HotelStrategyPO> getAll();
	
	public int update(HotelStrategyPO h);
	
	public int insert(HotelStrategyPO h);
	
	public int delete(HotelStrategyPO h);
}
