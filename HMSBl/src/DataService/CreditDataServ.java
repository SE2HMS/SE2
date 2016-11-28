package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CreditPO;

public interface CreditDataServ extends Remote {
    public ArrayList<CreditPO> getDetial(String id) throws RemoteException;

    public boolean insertCredit(CreditPO credit) throws RemoteException;
}
