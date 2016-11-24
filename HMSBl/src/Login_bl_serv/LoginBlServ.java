package Login_bl_serv;

import VO.LoginResult;
import VO.UserVO;

public interface LoginBlServ {

	/**
	 * 登录方法，如果没有这个用户，返回wrongId
	 * 如果密码不正确，返回wrongPassword
	 * 登录成功，返回success
	 * 登录冲突，返回alreadyLogin
	 * @param id 用户的id
	 * @param password 用户的密码
     * @return 返回一个登录结果
     */
	public LoginResult login(String id, String password);

	/**
	 * 注册一个用户，返回是否注册成功
	 * @param user 用户
	 * @return 注册结果
     */
	public boolean register(UserVO user);

	/**
	 * 得到用户信息
	 * 如果没登录或者没有这个id（不太可能出现）则返回null
	 * @param id 用户的id
	 * @return 返回用户的信息
     */
	public UserVO getUserInfo(String id);

	/**
	 * 注销登录
	 * @return 返回是否成功
     */
	public boolean logout(String id);
}
