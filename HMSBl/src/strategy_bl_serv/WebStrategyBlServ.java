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

    public boolean addDoubleElevenStrategy(String strategyName,double discount,Date startTime,Date endTime);

    public boolean addLevelStrategy(String strategyName,int upgradeCredit);

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
     * 这个参数似乎不太对
     *
     * @param strategy
     * @return
     */
    public boolean delStrategy(StrategyVO strategy);
}
