package blTest;

import static org.junit.Assert.*;

import login_bl_servlmpl.LoginBlServImpl;
import VO.LoginResult;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/24.
 * 这个测试只写了一部分，相当于还没实现
 */
public class LoginBlServImplTest {
    private LoginBlServImpl loginBlServImpl;

    @Before
    public void setup() {
        loginBlServImpl = new LoginBlServImpl();
    }

    @Test
    public void testLogin() {
        assertEquals(LoginResult.WRONG_ID,loginBlServImpl.oldLogin("wrong",""));
        assertEquals(LoginResult.WRONG_PASSWORD,loginBlServImpl.oldLogin("correct","wrong"));
        assertEquals(LoginResult.SUCCESS,loginBlServImpl.oldLogin("correct","correct"));
        assertEquals(LoginResult.WRONG_PASSWORD,loginBlServImpl.oldLogin("already","wrong"));
        assertEquals(LoginResult.ALREADY_LOGIN,loginBlServImpl.oldLogin("already","correct"));
    }

    @Test
    public void testGetInfo() {
        assertEquals(null,loginBlServImpl.getUserInfo(null));
        assertEquals(false,loginBlServImpl.getUserInfo("correct") == null);
    }

    @Test
    public void testLogout() {
        assertEquals(false,loginBlServImpl.logout("wrong"));
        //已经登录的情况
        assertEquals(true,loginBlServImpl.logout("correct"));
        //登出之后再来一次
        assertEquals(false,loginBlServImpl.logout("notLogin"));
    }
}
