package credit_bl_servlpml;

import PO.CreditPO;
import VO.CreditVO;
import credit_bl_serv.CreditBlServ;
import helper.ParseHelper;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
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

}
