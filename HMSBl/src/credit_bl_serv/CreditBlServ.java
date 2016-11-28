package credit_bl_serv;

import VO.CreditVO;
import credit_bl_servlpml.CreditBlServImpl;

public interface CreditBlServ {
    public static CreditBlServ getInstance() {
        return new CreditBlServImpl();
    }

    public CreditVO getCreditInfo(String id);

    public boolean modifyCredit(Operation operation);
}
