package login_bl_serv;

import VO.*;
import login_bl_servlmpl.LoginBlServImpl;

public interface LoginBlServ {

	public static LoginBlServ getInstance() {
		return new LoginBlServImpl();
	}

	/**
	 * 鐧诲綍鏂规硶锛屽鏋滄病鏈夎繖涓敤鎴凤紝杩斿洖WRONG_ID
	 * 濡傛灉瀵嗙爜涓嶆纭紝杩斿洖WRONG_PASSWORD
	 * 鐧诲綍鎴愬姛锛岃繑鍥濻UCCESS
	 * 鐧诲綍鍐茬獊锛岃繑鍥濧LREADY_LOGIN
	 * 鍏朵粬鎯呭喌锛岃繑鍥濫XCEPTION
	 * @param id 鐢ㄦ埛鐨刬d
	 * @param password 鐢ㄦ埛鐨勫瘑鐮�
     * @return 杩斿洖涓�涓櫥褰曠粨鏋�
     */
	public LoginResult login(String id, String password);

	/**
	 * 娉ㄥ唽涓�涓敤鎴凤紝杩斿洖鏄惁娉ㄥ唽鎴愬姛
	 * @param user 鐢ㄦ埛
	 * @return 娉ㄥ唽缁撴灉
     */
	public RegisterResult register(UserVO user, UserLoginInfo loginInfo);

	/**
	 * 寰楀埌鐢ㄦ埛淇℃伅
	 * 濡傛灉娌＄櫥褰曟垨鑰呮病鏈夎繖涓猧d锛堜笉澶彲鑳藉嚭鐜帮級鍒欒繑鍥瀗ull
	 * @param id 鐢ㄦ埛鐨刬d
	 * @return 杩斿洖鐢ㄦ埛鐨勪俊鎭�
     */
	public UserVO getUserInfo(String id);

	/**
	 * 娉ㄩ攢鐧诲綍
	 * @return 杩斿洖鏄惁鎴愬姛
     */
	public LogoutResult logout(String id);

	/**
	 * 注册网站营销人员
	 * @param name
	 * @param contact
     * @return
     */
	public RegisterResult registerWebSaler(String id,String password,String name ,String contact);

	/**
	 * 注册酒店工作人员
	 * @param hotelName
	 * @param contact
     * @return
     */
	public RegisterResult registerHotelStaff(String id,String password,String hotelName,String contact);
}
