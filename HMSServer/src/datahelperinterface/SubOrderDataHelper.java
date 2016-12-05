package datahelperinterface;

import java.util.ArrayList;

import datahelper.SubOrder;

public interface SubOrderDataHelper {
	public ArrayList<SubOrder> getAll();
	
	public int insert(SubOrder s);
}
