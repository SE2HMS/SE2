package credit_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.CreditDataServ;
import PO.CreditPO;
import datahelper.DataFactorylmpl;
import datahelperinterface.CreditDataHelper;
import datahelperinterface.DataFactory;

public class CreditDataServlmpl implements CreditDataServ{
	private ArrayList<CreditPO> list;
	private CreditDataHelper creditDataHelper;
	private DataFactory dataFactory;
	private static CreditDataServlmpl creditDataServlmpl;
	
	public static CreditDataServlmpl getInstance(){
		if(creditDataServlmpl==null)
			creditDataServlmpl=new CreditDataServlmpl();
		return creditDataServlmpl;
	}
	
	private CreditDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			creditDataHelper=dataFactory.getCreditDataHelper();
			list=creditDataHelper.getAll();
		}
	}

	@Override
	/*
	 * @param the id of user
	 * @return a list of creditPO include the details of credit change
	 * */
	public ArrayList<CreditPO> getDetial(String id) throws RemoteException {
		ArrayList<CreditPO> res=new ArrayList<CreditPO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUserID().equals(id)){
				res.add(list.get(i));
			}
		}
		return res;
	}

	@Override
	/*
	 * @param the CreditPO you need to insert
	 * @return a boolean 
	 * */
	public boolean insertCredit(CreditPO credit) throws RemoteException {
		int i=creditDataHelper.insert(credit);
		if(i==0){
			return false;
		}else{
			list=creditDataHelper.getAll();
			return true;
		}
	}

}
