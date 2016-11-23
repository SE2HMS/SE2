package credit_bl_servlpml;

import PO.CreditPO;
import VO.CreditVO;
import VO.OrderAction;
import credit_bl_serv.CreditBlServ;
import credit_bl_serv.Operation;
import rmi.RemoteHelper;

import java.util.Date;

public class CreditBlServlmpl implements CreditBlServ{

	@Override
	public CreditVO getCreditInfo(String id) {
		CreditPO creditPO = null;
		try {
			creditPO = RemoteHelper.getInstance().getCreditDataServ().getDetial(id);
		}catch (Exception e) {
			creditPO = null;
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
	/**
	 * 还没写
	 */
	public boolean modifyCredit(Operation operation) {
    	return false;
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
