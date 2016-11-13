package credit_data_servlmpl;

import java.rmi.RemoteException;

import PO.CreditPO;
import credit_data_serv.CreditDataServ;

public class CreditDataServlmpl implements CreditDataServ{

	@Override
	public double getTotel(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CreditPO getDetial(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCredit(CreditPO credit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedCredit(CreditPO credit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
