package DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.UserPO;

public interface UserDataServ extends Remote {

	public boolean insertUser(UserPO user) throws RemoteException;

	public UserPO getUser(String id) throws RemoteException;

	public boolean deleteUser(String id) throws RemoteException;

	public boolean modifiedUser(UserPO user) throws RemoteException;

	public ArrayList<UserPO> getUserList() throws RemoteException;

}
