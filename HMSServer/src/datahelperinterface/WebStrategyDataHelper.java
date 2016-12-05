package datahelperinterface;

import java.util.ArrayList;

import PO.WebStrategyPO;

public interface WebStrategyDataHelper {
	public ArrayList<WebStrategyPO> getAll();
	
	public int update(WebStrategyPO w);
	
	public int insert(WebStrategyPO w);
	
	public int delete(WebStrategyPO w);
}
