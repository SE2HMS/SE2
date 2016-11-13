package Login_bl_serv;

import PO.UserPO;

public interface LoginBlServ {
	public boolean isCorrect(String id,String password);
	public boolean register(UserPO user);
	public UserPO getUserInfo(String id);
}
