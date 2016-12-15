package credit_bl_servlpml;

import PO.CreditPO;
import VO.CreditVO;
import VO.OrderAction;
import VO.OrderState;
import VO.OrderVO;
import book_bl_serv.BookBlServ;
import credit_bl_serv.CreditBlServ;
import helper.ParseHelper;
import order_bl_serv.OrderBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * 12.3更新
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
        CreditVO creditVO = ParseHelper.toCreditVO(creditPO);
        return creditVO;
    }

    @Override
    public Iterator<CreditVO> getAllCreditInfo(String userId) {
        ArrayList<CreditPO> creditPOs;
        ArrayList<CreditVO> creditVOs = new ArrayList<>();
        try {
            creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
            for (CreditPO creditPO : creditPOs) {
                CreditVO creditVO = ParseHelper.toCreditVO(creditPO);
                creditVOs.add(creditVO);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return creditVOs.iterator();
    }

    @Override
    public boolean addCredit(CreditVO creditVO) {
        CreditPO creditPO = ParseHelper.toCreditPO(creditVO);
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

    @Override
    public void changeCredit(OrderVO orderVO) {
        Date time = Calendar.getInstance().getTime();
        String userId = orderVO.getUser().getId();
        String num = orderVO.getId();
        OrderAction orderAction = null;
        double change = 0;
        double total = this.getTotal(userId);
        if(orderVO.getState() == OrderState.FINISH) {
            change = orderVO.getTotal();
            orderAction = OrderAction.CHECK_IN;
        }else if(orderVO.getState() == OrderState.REVOKE) {
            change = orderVO.getTotal()/2;
            orderAction = OrderAction.REVOKE;
        }else if(orderVO.getState() == OrderState.ABNORMAL) {
            change = -orderVO.getTotal();
            orderAction = OrderAction.ABNORMAL;
        }
        total += change;
        CreditVO creditVO = new CreditVO(time,num,userId,orderAction,change,total);
        this.addCredit(creditVO);
    }

    @Override
    public boolean addCredit(String userId, String action,double num) {
        if(userId == null) {
            return false;
        }
        boolean success = false;
        try {
            Date time = RemoteHelper.getInstance().getTimeServ().getTime();
            String timeString = ParseHelper.dateToString(time);
            double total = CreditBlServ.getInstance().getTotal(userId);
            total += num;
            CreditPO creditPO = new CreditPO(null,timeString,userId,total,num,action);
            success = RemoteHelper.getInstance().getCreditDataServ().insertCredit(creditPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean charge(String userId, double num) {
        double total = this.getTotal(userId);
        total += num;
        Date time = null;
        try {
            time = RemoteHelper.getInstance().getTimeServ().getTime();
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        String timeString = ParseHelper.dateToString(time);
        CreditPO creditPO = new CreditPO(null,timeString,userId,total,num,OrderAction.RECHARGE.toString());
        boolean success = false;
        try {
            success = RemoteHelper.getInstance().getCreditDataServ().insertCredit(creditPO);
        }catch (RemoteException e) {
            e.printStackTrace();
        }
        return success;
    }
}
