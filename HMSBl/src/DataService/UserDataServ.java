package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import PO.UserPO;

public interface UserDataServ extends Remote {
	
	public void insertUser(UserPO user) throws RemoteException;
	
	public UserPO getUser(String id) throws RemoteException;
	
	public void deleteUser (String id) throws RemoteException;
	
	public void modifiedUser (UserPO user) throws RemoteException;
	
	public UserPO[] getUserList () throws RemoteException;
	
	public void init() throws RemoteException;
	
	public void finish() throws RemoteException;
}
