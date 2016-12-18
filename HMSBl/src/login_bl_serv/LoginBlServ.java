package login_bl_serv;

import VO.*;
import login_bl_servlmpl.LoginBlServImpl;

public interface LoginBlServ {

    public static LoginBlServ getInstance() {
        return new LoginBlServImpl();
    }

    /**
     * 登录方法
     * 如果正确返回成功
     * 如果id错误，密码错误，则返回相应错误
     * 如果登录冲突，返回登录冲突
     * 其他情况返回异常
     *
     * @param id       用户的id
     * @param password 用户的密码
     * @return 返回登录结果
     */
    public LoginResult login(String id, String password);

    /**
     * 这个方法用来注册正常的用户
     * 用户可以是NORMAL或者SPECIAL
     *
     * @param user      用户VO
     * @param loginInfo 登录信息，实际上id不用填
     * @return 返回注册结果
     */
    public RegisterResult register(UserVO user, UserLoginInfo loginInfo);

    /**
     * 得到用户的信息
     *
     * @param id 用户的id
     * @return 该用户的信息
     */
    public UserVO getUserInfo(String id);

    /**
     * 根据id得到一个网站销售人员的信息
     *
     * @param id 网站销售人员id
     * @return 返回网站销售人员
     */
    public WebSaler getWebSaler(String id);

    /**
     * 得到酒店工作人员的信息
     *
     * @param id 酒店工作人员的id
     * @return 返回该酒店工作人员的信息
     */
    public HotelStaff getHotelStaff(String id);

    /**
     * 只能给UserVO的用户用
     * 也就是NORMAL和SPECIAL类型
     * 会检查是否有名字和联系方式冲突的情况，返回错误
     *
     * @param name        用户名
     * @param specialInfo 对应生日或者公司名称
     * @param contact     联系方式
     * @return 返回是否成功
     */
    public boolean modifyUserInfo(String id, String name, String specialInfo, String contact);

    /**
     * 得到网站管理人员的信息
     *
     * @param id 网站管理人员的id
     * @return 返回得到的信息
     */
    public WebManager getWebManager(String id);

    /**
     * 登出
     *
     * @param id 用户id
     * @return 返回是否成功
     */
    public LogoutResult logout(String id);

    /**
     * 注册网站销售人员
     *
     * @param name     用户民
     * @param contact  联系方式
     * @param password 密码
     * @return 返回注册结果
     */
    public RegisterResult registerWebSaler(String password, String name, String contact);

    /**
     * 注册酒店工作人员
     *
     * @param hotelName 酒店名称
     * @param contact   联系方式
     * @param password  密码
     * @param userName  用户名
     * @return 返回注册结果
     */
    public RegisterResult registerHotelStaff(String password, String hotelName, String contact, String userName);

    /**
     * 注册网站管理人员，只能有一个
     *
     * @param password
     * @param name
     * @param contact
     * @return
     */
    public RegisterResult registerWebManager(String password, String name, String contact);

}
