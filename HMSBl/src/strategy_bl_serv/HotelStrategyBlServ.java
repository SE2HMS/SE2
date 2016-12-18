package strategy_bl_serv;

import VO.StrategyVO;
import strategy_bl_servlmpl.HotelStrategyBlServlmpl;

import java.util.Date;
import java.util.Iterator;

public interface HotelStrategyBlServ {

    /**
     * 得到此接口的一个实例
     *
     * @return
     */
    public static HotelStrategyBlServ getInstance() {
        return new HotelStrategyBlServlmpl();
    }

    /**
     * 得到一个酒店的所有促销策略的迭代器
     *
     * @param hotelName
     * @return
     */
    public Iterator<StrategyVO> getStrategy(String hotelName);

    /**
     * 传入酒店名和策略名
     * 得到一条策略
     *
     * @param hotelName    酒店名
     * @param strategyName 策略名
     * @return 返回一个策略
     */
    public StrategyVO getOneStrategy(String hotelName, String strategyName);

    public double getMinDiscount(String hotelName, String userId, Date inTime, int roomNum);

    /**
     * 给某个酒店增加一条促销策略
     *
     * @param hotelName 策略所属酒店名
     * @param strategy  增加的策略
     * @return
     */
    public boolean addStrategy(String hotelName, StrategyVO strategy);

    /**
     * 修改一个促销策略
     *
     * @param hotelName 策略所属酒店名
     * @param strategy  修改后的策略
     * @return
     */
    public boolean modifyStrategy(String hotelName, StrategyVO strategy);

    /**
     * 删除一个促销策略
     *
     * @param hotelName 策略所属酒店名
     * @param strategy  应删除的策略
     * @return
     */
    public boolean delStrategy(String hotelName, StrategyVO strategy);
}
