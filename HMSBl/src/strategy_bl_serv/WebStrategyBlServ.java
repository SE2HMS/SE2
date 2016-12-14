package strategy_bl_serv;

import VO.StrategyVO;
import strategy_bl_servlmpl.WebStrategyBlServlmpl;

import java.util.Date;
import java.util.Iterator;

public interface WebStrategyBlServ {

	public static WebStrategyBlServ getInstance() {
		return new WebStrategyBlServlmpl();
	}

	/**
	 * 得到所有网站促销策略
	 * @return
     */
	public Iterator<StrategyVO> getStrategy();

	public double getMinDiscount(String useId, Date inTime,String CBD);

	/**
	 * 增加一条策略
	 * @param strategy
	 * @return
     */
	public boolean addStrategy(StrategyVO strategy);

	/**
	 * 修改一条策略
	 * @param strategy
	 * @return
     */
	public boolean modifyStrategy(StrategyVO strategy);

	/**
	 * 删除一条策略
	 * @param strategy
	 * @return
     */
	public boolean delStrategy(StrategyVO strategy);
}
