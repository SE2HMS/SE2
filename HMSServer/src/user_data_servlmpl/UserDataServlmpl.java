package user_data_servlmpl;

import java.rmi.RemoteException;

import PO.UserPO;
import user_data_serv.UserDataServ;

public class UserDataServlmpl implements UserDataServ {

	@Override
	public void insertUser(UserPO user) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserPO getUser(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifiedUser(UserPO user) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserPO[] getUserList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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