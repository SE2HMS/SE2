package datahelperinterface;

import java.util.ArrayList;

import PO.CreditPO;

public interface CreditDataHelper {
	public ArrayList<CreditPO> getAll();
	
	public int insert(CreditPO c);
	
	
}
