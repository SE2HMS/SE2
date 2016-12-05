package datahelperinterface;

import java.util.ArrayList;

import PO.RoomPO;

public interface RoomDataHelper {
	public ArrayList<RoomPO> getAll();
	
	public int update(RoomPO r);
	
	public int insert(RoomPO r);
	
	public int delete(RoomPO r);
}
