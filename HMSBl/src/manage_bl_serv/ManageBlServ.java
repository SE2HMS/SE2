package manage_bl_serv;

import PO.UserPO;

public interface ManageBlServ {
	public boolean getAllUserInfo(UserPO user);
	public boolean addUserInfo(UserPO user);
	public boolean modifyUserInfo(UserPO user);
	public boolean delUserInfo(UserPO user);
}
