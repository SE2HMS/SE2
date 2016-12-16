package credit_bl_servlpml;

import PO.CreditPO;
import VO.CreditVO;
import VO.OrderAction;
import VO.OrderState;
import VO.OrderVO;
import credit_bl_serv.CreditBlServ;
import helper.ParseHelper;
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
        if(userId == null || creditId == null) {
            return null;
        }
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
        if(userId == null) {
            return null;
        }
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
        if(creditVO == null) {
            return false;
        }
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
        if(userId == null) {
            return 0;
        }
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
        if(orderVO == null) {
            return;
        }
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
            if((orderVO.getExecTime().getTime() - time.getTime()) > 3600 * 6 ) {
                change = orderVO.getTotal() / 2;
                orderAction = OrderAction.REVOKE;
            }
        }else if(orderVO.getState() == OrderState.ABNORMAL) {
            change = -orderVO.getTotal();
            orderAction = OrderAction.ABNORMAL;
        }
        total += change;
        CreditVO creditVO = new CreditVO(time,num,userId,orderAction,change,total);
        this.addCredit(creditVO);
    }

    @Override
    public boolean charge(String userId, double num) {
        if(userId == null) {
            return false;
        }
        double total = this.getTotal(userId);
        total += num * 100;
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
