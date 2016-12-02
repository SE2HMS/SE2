package credit_bl_servlpml;

import PO.CreditPO;
import VO.CreditVO;
import VO.OrderAction;
import credit_bl_serv.CreditBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * 11.29这个大概是写好了
 * 测试用例还没写
 * 还有关于OrderAction的字符有哪些
 * 以及时间的转换怎么复用的问题
 */
public class CreditBlServImpl implements CreditBlServ {

    @Override
    public CreditVO getCreditInfo(String userId, String creditId) {
        CreditPO creditPO = null;
        try {
            ArrayList<CreditPO> creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
            for (CreditPO oneCreditPO : creditPOs) {
                if (creditPO.getID().equals(creditId)) ;
                creditPO = oneCreditPO;
            }
        } catch (Exception e) {
            return null;
        }
        CreditVO creditVO = parsrCreditVO(creditPO);
        return creditVO;
    }

    @Override
    public Iterator<CreditVO> getAllCreditInfo(String userId) {
        ArrayList<CreditPO> creditPOs;
        ArrayList<CreditVO> creditVOs = new ArrayList<>();
        try {
            creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
            for (CreditPO creditPO : creditPOs) {
                CreditVO creditVO = parsrCreditVO(creditPO);
                creditVOs.add(creditVO);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return creditVOs.iterator();
    }

    @Override
    public boolean addCredit(CreditVO creditVO) {
        CreditPO creditPO = parseCreditPO(creditVO);
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getCreditDataServ().insertCredit(creditPO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public double getTotal(String userId) {
        double total = 0;
        try {
            ArrayList<CreditPO> creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
            for (CreditPO creditPO : creditPOs) {
                total += creditPO.getTotel();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 写好了，把creditVO变成creditPO
     * 需要看一下那边的字符串有哪些
     *
     * @param creditVO
     * @return
     */
    private CreditPO parseCreditPO(CreditVO creditVO) {
        if (creditVO == null) {
            return null;
        }
        String id = creditVO.getNum();
        String time = creditVO.getTime().toString();
        String userId = creditVO.getUserId();
        double total = creditVO.getCredit();
        double change = creditVO.getCreditChange();
        OrderAction action = creditVO.getAction();
        String bh = "";
        switch (action) {
            case abnormal:
                bh = "ABNORMAL";
                break;
            case recharge:
                bh = "recharge";
                break;
            case check_in:
                bh = "check_in";
                break;
            case revoke:
                bh = "REVOKE";
                break;
        }
        CreditPO creditPO = new CreditPO(id, time, userId, total, change, bh);
        return creditPO;
    }

    /**
     * 写好了，用来把creditPO变成VO
     *
     * @param creditPO
     * @return
     */
    private CreditVO parsrCreditVO(CreditPO creditPO) {
        if (creditPO == null) {
            return null;
        }
        String date = creditPO.getTime();
        String orderId = creditPO.getID();
        String userId = creditPO.getUserID();
        String action = creditPO.getBehavior();
        double creditChange = creditPO.getChange();
        double credit = creditPO.getTotel();
        CreditVO creditVO = new CreditVO(stringToDate(date), orderId, userId, stringToAction(action), creditChange, credit);
        return creditVO;
    }

    /**
     * 好了，是复制过来的，到时候想想办法怎么重新搞一下
     * @param date
     * @return
     */
    private Date stringToDate(String date) {
        String[] splitString = date.split(" ");
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int month = 0;
        for (int i = 0; i < months.length; i++) {
            if (splitString[1].equals(months[i])) {
                month = i;
                break;
            }
        }
        int day = Integer.parseInt(splitString[2]);
        int year = Integer.parseInt(splitString[5]);
        String[] splitTimeInDay = splitString[3].split(":");
        int hour = Integer.parseInt(splitTimeInDay[0]);
        int min = Integer.parseInt(splitTimeInDay[1]);
        int sec = Integer.parseInt(splitTimeInDay[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min, sec);
        return calendar.getTime();
    }

    /**
     * 这个则是把字符串转成OrderAction类
     * @param action
     * @return
     */
    private OrderAction stringToAction(String action) {
        OrderAction result = null;
        switch (action) {
            case "ABNORMAL":
                result = OrderAction.abnormal;
                break;
            case "recharge":
                result = OrderAction.recharge;
                break;
            case "check_in":
                result = OrderAction.check_in;
                break;
            case "REVOKE":
                result = OrderAction.revoke;
                break;
        }
        return result;
    }
}
