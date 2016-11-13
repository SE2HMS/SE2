package credit_bl_serv;

import PO.CreditPO;

public interface CreditBlServ {
	public CreditPO getCreditInfo(String id);
	public boolean modifyCredit(Operation operation);
}
