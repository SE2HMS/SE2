package strategy_bl_serv;

import PO.StrategyPO;

public interface WebStrategyBlServ {
	public StrategyPO[] getStrategy(String hotelName);
	public boolean addStrategy(StrategyPO strategy);
	public boolean modifyStrategy(StrategyPO strategy);
	public boolean delStrategy(StrategyPO strategy);
}
