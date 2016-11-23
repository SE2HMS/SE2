package credit_bl_serv;

import VO.CreditVO;

public interface CreditBlServ {
    public CreditVO getCreditInfo(String id);

    public boolean modifyCredit(Operation operation);
}
