package strategy_bl_servlmpl;

import VO.StrategyVO;
import strategy_bl_serv.WebStrategyBlServ;

import java.util.Iterator;

public class WebStrategyBlServlmpl implements WebStrategyBlServ{

	@Override
	public Iterator<StrategyVO> getStrategy() {
		return null;
	}

	@Override
	public boolean addStrategy(StrategyVO strategy) {
		return false;
	}

	@Override
	public boolean modifyStrategy(StrategyVO strategy) {
		return false;
	}

	@Override
	public boolean delStrategy(StrategyVO strategy) {
		return false;
	}
}
