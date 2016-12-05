package datahelperinterface;

import java.util.ArrayList;

import PO.UserPO;

public interface UserDataHelper {
	public ArrayList<UserPO> getAll();
	
	public int update(UserPO user);
	
	public int insert(UserPO user);
	
	public int delete(String id);
}
