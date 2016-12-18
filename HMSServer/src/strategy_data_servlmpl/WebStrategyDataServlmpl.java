package strategy_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.WebStrategyDataServ;
import PO.WebStrategyPO;
import datahelper.DataFactorylmpl;
import datahelperinterface.DataFactory;
import datahelperinterface.WebStrategyDataHelper;

public class WebStrategyDataServlmpl implements WebStrategyDataServ{
	private ArrayList<WebStrategyPO> list;
	private DataFactory dataFactory;
	private WebStrategyDataHelper webStrategyDataHelper;
	private static WebStrategyDataServlmpl webStrategyDataServlmpl;
	
	public static WebStrategyDataServlmpl getInstance(){
		if(webStrategyDataServlmpl==null)
			webStrategyDataServlmpl=new WebStrategyDataServlmpl();
		return webStrategyDataServlmpl;
	}
	
	private WebStrategyDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			webStrategyDataHelper=dataFactory.getWebStrategyDataHelper();
			list=webStrategyDataHelper.getAll();
		}
	}

	@Override
	public boolean insertWebStrategy(WebStrategyPO strategyPO) throws RemoteException {
		int i=webStrategyDataHelper.insert(strategyPO);
		if(i==0)
			return false;
		else{
			list=webStrategyDataHelper.getAll();
			return true;
		}
	}

	@Override
	public ArrayList<WebStrategyPO> getWebStrategyList() throws RemoteException {
		ArrayList<WebStrategyPO> res=new ArrayList<WebStrategyPO>();
		for(int i=0;i<list.size();i++){
			res.add(list.get(i));
		}
		return res;
	}

	@Override
	public boolean deleteWebStrategy(WebStrategyPO w) throws RemoteException {
		int i=webStrategyDataHelper.delete(w);
		if(i==0)
			return false;
		else{
			list=webStrategyDataHelper.getAll();
			return true;
		}
	}

	@Override
	public boolean modifiedWebStrategy(WebStrategyPO strategy) throws RemoteException {
		int i=webStrategyDataHelper.update(strategy);
		if(i==0)
			return false;
		else{
			list=webStrategyDataHelper.getAll();
			return true;
		}
	}



}
