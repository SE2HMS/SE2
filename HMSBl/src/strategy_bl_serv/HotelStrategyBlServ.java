package strategy_bl_serv;

import VO.StrategyVO;

import java.util.Iterator;

public interface HotelStrategyBlServ {

	/**
	 * 得到一个酒店的所有促销策略的迭代器
	 * @param hotelName
	 * @return
     */
	public Iterator<StrategyVO> getStrategy(String hotelName);

	/**
	 * 给某个酒店增加一条促销策略
	 * @param hotelName 策略所属酒店名
	 * @param strategy 增加的策略
	 * @return
     */
	public boolean addStrategy(String hotelName,StrategyVO strategy);

	/**
	 * 修改一个促销策略
	 * @param hotelName 策略所属酒店名
	 * @param strategy 修改后的策略
	 * @return
     */
	public boolean modifyStrategy(String hotelName,StrategyVO strategy);

	/**
	 * 删除一个促销策略
	 * @param hotelName 策略所属酒店名
	 * @param strategy 应删除的策略
     * @return
     */
	public boolean delStrategy(String hotelName,StrategyVO strategy);
}
