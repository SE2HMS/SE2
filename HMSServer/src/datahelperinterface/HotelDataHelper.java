package datahelperinterface;

import java.util.ArrayList;

import PO.HotelPO;

public interface HotelDataHelper {
	public ArrayList<HotelPO> getAll();
	
	public int insert(HotelPO h);
	
	public int update(HotelPO h);
	
	
}
