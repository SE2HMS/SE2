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
        assertEquals(LoginResult.wrongId,loginBlServImpl.login("wrong",""));
        assertEquals(LoginResult.wrongPassword,loginBlServImpl.login("correct","wrong"));
        assertEquals(LoginResult.success,loginBlServImpl.login("correct","correct"));
        assertEquals(LoginResult.wrongPassword,loginBlServImpl.login("already","wrong"));
        assertEquals(LoginResult.alreadyLogin,loginBlServImpl.login("already","correct"));
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
