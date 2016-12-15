package credit_bl_serv;

import VO.CreditVO;
import VO.OrderState;
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

    public boolean addCredit(String userId,String action,double num);

    /**
     * 得到一条具体的信用记录
     * @param userId 用户id
     * @param creditId 信用id
     * @return
     */
    public CreditVO getCreditInfo(String userId,String creditId);

    /**
     * 每次订单状态更改都要调用这个
     * @param orderVO
     */
    public void changeCredit(OrderVO orderVO);

    /**
     * 得到一个用户的所有信用记录
     * @param userId 用户id
     * @return
     */
    public Iterator<CreditVO> getAllCreditInfo(String userId);

    public boolean charge(String userId,double num);

    /**
     * 增加一条信用记录
     * @param creditVO
     * @return
     */
    public boolean addCredit(CreditVO creditVO);

    /**
     * 得到一个用户的总信用值
     * @param userId
     * @return
     */
    public double getTotal(String userId);
}
