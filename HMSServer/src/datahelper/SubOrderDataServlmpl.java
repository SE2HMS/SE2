package datahelper;

import java.util.ArrayList;

import datahelperinterface.DataFactory;
import datahelperinterface.SubOrderDataHelper;
import datahelperinterface.SubOrderDataServ;

public class SubOrderDataServlmpl implements SubOrderDataServ{
	private ArrayList<SubOrder> list;
	private DataFactory dataFactory;
	private SubOrderDataHelper subOrderDataHelper;
	private static SubOrderDataServlmpl subOrderDataServlmpl;
	
	public static SubOrderDataServlmpl getInstance(){
		if(subOrderDataServlmpl==null)
			subOrderDataServlmpl=new SubOrderDataServlmpl();
		return subOrderDataServlmpl;
	}
	
	private SubOrderDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			subOrderDataHelper=dataFactory.getSubOrderDataHelper();
			list=subOrderDataHelper.getAll();
		}
	}

	@Override
	public ArrayList<SubOrder> getSubOrders(String orderid) {
		ArrayList<SubOrder> res=new ArrayList<SubOrder>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getOrderID().equals(orderid)){
				res.add(list.get(i));
			}
		}
		return res;
	}

	@Override
	public boolean insertSubOrder(SubOrder s) {
		int i=subOrderDataHelper.insert(s);
		if(i==0)
			return false;
		else{
			list=subOrderDataHelper.getAll();
			return true;
		}
	}

}
