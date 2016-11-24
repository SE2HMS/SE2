package Login_bl_servlmpl;

import Login_bl_serv.LoginBlServ;
import PO.UserPO;
import VO.LoginResult;
import VO.UserVO;
import rmi.RemoteHelper;

import java.rmi.RemoteException;

public class LoginBlServlmpl implements LoginBlServ{

	@Override
	/**
	 * 登录方法，如果没有这个用户，返回wrongId
	 * 如果密码不正确，返回wrongPassword
	 * 登录成功，返回success
	 * 登录冲突尚未实现
	 */
	public LoginResult login(String id, String password) {
		UserPO userPO = null;
		try {
			userPO = RemoteHelper.getInstance().getUserDateServ().getUser(id);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		if(userPO == null) {
			return LoginResult.wrongId;
		}
		boolean correct = password.equals(userPO.getPassword());
		if(correct) {
			return LoginResult.success;
		}else {
			return LoginResult.wrongPassword;
		}
	}

	@Override
	/**
	 * 注册一个用户，成功返回true
	 * 失败返回false
	 */
	public boolean register(UserVO user) {
		return false;
	}

	@Override
	public boolean logout(String id) {
		return false;
	}

	@Override
	public UserVO getUserInfo(String id) {
		return null;
	}
}
