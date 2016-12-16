package credit_bl_serv;

import VO.CreditVO;
import VO.OrderVO;
import credit_bl_servlpml.CreditBlServImpl;

import java.util.Iterator;

public interface CreditBlServ {

    /**
     * 得到此接口的一个实现的一个实例
     * @return
     */
    public static CreditBlServ getInstance() {
        return new CreditBlServImpl();
    }

    /**
     * 得到一条具体的信用记录
     * 这个似乎界面层还是用不到，差不多该删了
     * @param userId 用户id
     * @param creditId 信用id，显然是没人知道的
     * @return
     */
    public CreditVO getCreditInfo(String userId,String creditId);

    /**
     * 每次订单状态更改都要调用这个
     * 订单状态改为异常时，扣订单价格全部的信用
     * 订单状态改为入住，增加订单价格的信用
     * 改为撤销，如果在最晚入住时间六小时内，扣一半的信用
     * 改为未执行，是不可能的，所以直接啥都不干
     * @param orderVO 该订单
     */
    public void changeCredit(OrderVO orderVO);

    /**
     * 得到一个用户的所有信用记录
     * @param userId 用户id
     * @return
     */
    public Iterator<CreditVO> getAllCreditInfo(String userId);

    /**
     * 如果传入id为空，返回错误
     * 信用充值，生成一条增加信用的记录
     * 并增加用户的信用，返回是否成功
     * 增加信用为传入钱数的100倍
     * @param userId 用户id
     * @param num 充值数量（传入钱数）
     * @return
     */
    public boolean charge(String userId,double num);

    /**
     * 增加一条信用记录
     * 这个大概该改成私有方法啦
     * @param creditVO
     * @return
     */
    public boolean addCredit(CreditVO creditVO);

    /**
     * 得到一个用户的总信用值
     * 这个相当好用
     * 如果是酒店工作人员，网站管理人员，网站销售人员的id
     * 则返回0
     * @param userId 用户的id
     * @return
     */
    public double getTotal(String userId);
}
