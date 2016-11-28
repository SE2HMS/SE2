package strategy_bl_servlmpl;

import VO.StrategyVO;
import strategy_bl_serv.HotelStrategyBlServ;

import java.util.Iterator;

public class HotelStrategyBlServlmpl implements HotelStrategyBlServ{
	@Override
	public Iterator<StrategyVO> getStrategy(String hotelName) {
		return null;
	}

	@Override
	public boolean addStrategy(String hotelName, StrategyVO strategy) {
		return false;
	}

	@Override
	public boolean modifyStrategy(String hotelName, StrategyVO strategy) {
		return false;
	}

	@Override
	public boolean delStrategy(String hotelName, StrategyVO strategy) {
		return false;
	}
}
