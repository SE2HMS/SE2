package user_data_servlmpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.CreditDataServ;
import DataService.UserDataServ;
import PO.UserPO;
import com.sun.org.apache.regexp.internal.RE;
import credit_data_servlmpl.CreditDataServlmpl;
import datahelper.DataFactorylmpl;
import datahelperinterface.DataFactory;
import datahelperinterface.UserDataHelper;

public class UserDataServlmpl implements UserDataServ {
	private ArrayList<UserPO> list;
	private DataFactory dataFactory;
	private UserDataHelper userDataHelper;
	private CreditDataServ creditDataServ;
	private static UserDataServlmpl userDataServlmpl;
	
	public static UserDataServlmpl getInstance(){
		if(userDataServlmpl==null)
			userDataServlmpl=new UserDataServlmpl();
		return userDataServlmpl;
	}
	
	private UserDataServlmpl(){
		if(list==null){
			dataFactory=new DataFactorylmpl();
			userDataHelper=dataFactory.getUserDataHelper();
			list=userDataHelper.getAll();
			creditDataServ=CreditDataServlmpl.getInstance();
		}
	}

	@Override
	public boolean insertUser(UserPO user) throws RemoteException {
		boolean tag=true;
		if(user.getCredit()!=null)
		for(int i=0;i<user.getCredit().size();i++){
			if(!tag)
				break;
			tag=creditDataServ.insertCredit(user.getCredit().get(i));
		}
		int i=userDataHelper.insert(user);
		if(i==0)
			tag=false;
		else
			list=userDataHelper.getAll();
		return tag;
	}

	@Override
	public UserPO getUser(String id) throws RemoteException {
		UserPO user=null;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getID().equals(id)){
				user=list.get(i);
				break;
			}
		}
		
		if(user==null)
			return user;
		user.setCredit(creditDataServ.getDetial(user.getID()));
		return user;
	}




	public UserPO getUser(String userName,String contact) throws RemoteException {
		UserPO usr = null;
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getName().equals(userName) && list.get(i).getContactInfo().equals(contact)) {
				usr = list.get(i);
				break;
			}
		}
		return usr;
	}

	@Override
	public boolean deleteUser(String id) throws RemoteException {
		int i=userDataHelper.delete(id);
		if(i==0)
			return false;
		else{
			list=userDataHelper.getAll();
			return true;
		}
	}

	@Override
	public boolean modifiedUser(UserPO user) throws RemoteException {
		int i=userDataHelper.update(user);
		if(i==0)
			return false;
		else{
			list=userDataHelper.getAll();
			return true;
		}
	}

	@Override
	public ArrayList<UserPO> getUserList() throws RemoteException {
		ArrayList<UserPO> res=new ArrayList<UserPO>();
		for(int i=0;i<list.size();i++)
			res.add(list.get(i));
		return res;
	}
}
