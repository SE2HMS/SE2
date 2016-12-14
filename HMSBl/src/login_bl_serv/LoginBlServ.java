package login_bl_serv;

import VO.*;
import login_bl_servlmpl.LoginBlServImpl;

public interface LoginBlServ {

	public static LoginBlServ getInstance() {
		return new LoginBlServImpl();
	}

	/**
	 * 登录，似乎已经没有什么卵用可以删掉了
	 * @param id
	 * @param password
     * @return
     */
	public LoginResult login(String id, String password);

	/**
	 * 这个方法用来注册正常的用户
	 * @param user
	 * @param loginInfo
     * @return
     */
	public RegisterResult register(UserVO user, UserLoginInfo loginInfo);

	/**
	 * 得到用户的信息
	 * @param id
	 * @return
     */
	public UserVO getUserInfo(String id);

	public WebSaler getWebSaler(String id);

	public HotelStaff getHotelStaff(String id);

	public WebManager getWebManager(String id);
	/**
	 * 登出
	 * @param id
	 * @return
     */
	public LogoutResult logout(String id);

	/**
	 * 注册网站营销人员
	 * @param name
	 * @param contact
     * @return
     */
	public RegisterResult registerWebSaler(String password,String name ,String contact);

	/**
	 * 注册酒店工作人员
	 * @param hotelName
	 * @param contact
     * @return
     */
	public RegisterResult registerHotelStaff(String password,String hotelName,String contact,String userName);

	/**
	 * 注册网站管理人员，只能有一个
	 * @param password
	 * @param name
	 * @param contact
     * @return
     */
	public RegisterResult registerWebManager(String password,String name,String contact);

}
