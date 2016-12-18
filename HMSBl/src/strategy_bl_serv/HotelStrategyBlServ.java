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

    /**
     * 得到一个酒店所有策略中适合某个用户的最低折扣
     *
     * @param hotelName 酒店名
     * @param userId    用户id
     * @param inTime    入住时间
     * @param roomNum   房间数量
     * @return 返回折扣，如果没有，则返回1
     */
    public double getMinDiscount(String hotelName, String userId, Date inTime, int roomNum);

    /**
     * 给某个酒店增加一条促销策略
     *
     * @param hotelName 策略所属酒店名
     * @param strategy  增加的策略
     * @return 返回是否成功
     */
    public boolean addStrategy(String hotelName, StrategyVO strategy);

    /**
     * 修改一条酒店营销策略
     *
     * @param hotelName    酒店名
     * @param strategyName 策略名
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param discount     折扣
     * @return 返回是否成功
     */
    public boolean modifyStrategy(String hotelName, String strategyName, Date startTime, Date endTime, double discount);

    /**
     * 修改一个促销策略
     *
     * @param hotelName 策略所属酒店名
     * @param strategy  修改后的策略
     * @return 返回是否成功
     */
    public boolean modifyStrategy(String hotelName, StrategyVO strategy);

    /**
     * 删除一条酒店营销策略
     *
     * @param hotelName    酒店名
     * @param strategyName 策略名
     * @return 返回是否成功
     */
    public boolean delStrategy(String hotelName, String strategyName);
}
