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
     *
     * @return
     */
    public Iterator<StrategyVO> getStrategy();

    /**
     * 得到目前的最低折扣
     * 根据用户id，入住时间和所在商圈
     * 返回最低折扣
     * 如果没有就返回1，不打折
     *
     * @param useId  用户id
     * @param inTime 入住时间
     * @param CBD    商圈
     * @return 折扣
     */
    public double getMinDiscount(String useId, Date inTime, String CBD);

    /**
     * 增加一个特殊日期的策略
     * @param strategyName 策略名
     * @param discount 折扣
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回是否成功
     */
    public boolean addDoubleElevenStrategy(String strategyName,double discount,Date startTime,Date endTime);

    /**
     * 增加一个等级策略
     * @param strategyName 策略名称
     * @param upgradeCredit 升级信用
     * @return 返回是否成功
     */
    public boolean addLevelStrategy(String strategyName,int upgradeCredit);

    /**
     * 增加一条商圈策略
     * @param strategyName 策略名
     * @param lev0 最低等级折扣
     * @param lev1 升一级后折扣
     * @param lev2 最高级折扣
     * @param CBD 商圈名称
     * @return 返回是否成功
     */
    public boolean addCBDStrategy(String strategyName,double lev0,double lev1,double lev2,String CBD);

    /**
     * 增加一条策略
     * @param strategy 该策略
     * @return 返回是否成功
     */
    public boolean addStrategy(StrategyVO strategy);

    /**
     * 修改一条策略
     * 讲道理这个可以删了
     *
     * @param strategy 该策略
     * @return 返回是否成功
     */
    public boolean modifyStrategy(StrategyVO strategy);

    /**
     * 删除一条策略
     * @param strategyName 策略名称
     * @return 返回是否成功
     */
    public boolean delStrategy(String strategyName);
}
