package credit_bl_servlpml;

import PO.CreditPO;
import VO.CreditVO;
import VO.OrderAction;
import credit_bl_serv.CreditBlServ;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CreditBlServImpl implements CreditBlServ{

	@Override
	public CreditVO getCreditInfo(String userId,String creditId) {
		CreditPO creditPO = null;
		try {
			ArrayList<CreditPO> creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
			for(CreditPO oneCreditPO : creditPOs) {
				if(creditPO.getID().equals(creditId));
				creditPO = oneCreditPO;
			}
		}catch (Exception e) {
			return null;
		}
		String date = creditPO.getTime();
		String orderId = creditPO.getID();
		String action = creditPO.getBehavior();
		double creditChange = creditPO.getChange();
		double credit = creditPO.getTotel();
		CreditVO creditVO = new CreditVO(stringToDate(date),orderId,stringToAction(action),creditChange,credit);
		return creditVO;
	}

	@Override
	public Iterator<CreditVO> getAllCreditInfo(String userId) {
		ArrayList<CreditPO> creditPOs;
		ArrayList<CreditVO> creditVOs = new ArrayList<>();
		try {
			creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
			for(CreditPO creditPO:creditPOs) {
				CreditVO creditVO = parsrCreditVO(creditPO);
				creditVOs.add(creditVO);
			}
		}catch (RemoteException e) {
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
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public double getTotal(String userId) {
		double total = 0;
		try {
			ArrayList<CreditPO> creditPOs = RemoteHelper.getInstance().getCreditDataServ().getDetial(userId);
			for(CreditPO creditPO:creditPOs) {
				total += creditPO.getTotel();
			}
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 这个还没写
	 * @param creditVO
	 * @return
     */
	private CreditPO parseCreditPO(CreditVO creditVO) {
		return null;
	}

	/**
	 * 这个还没写，写完要把第一个方法里的东西也改掉
	 * @param creditPO
	 * @return
     */
	private CreditVO parsrCreditVO(CreditPO creditPO) {
		return null;
	}

	/**
	 * 还没写完这个
	 * @param date
	 * @return
     */
	private Date stringToDate(String date) {
		return new Date();
	}

	/**
	 *
	 * @param action
	 * @return
     */
	private OrderAction stringToAction(String action) {
		return OrderAction.abnormal;
	}
}
