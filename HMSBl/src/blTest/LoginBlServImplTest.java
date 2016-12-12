package blTest;

import static org.junit.Assert.*;

import VO.LoginResult;
import login_bl_servlmpl.LoginBlServImpl;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2016/11/24.
 * 杩欎釜娴嬭瘯鍙啓浜嗕竴閮ㄥ垎锛岀浉褰撲簬杩樻病瀹炵幇
 */
public class LoginBlServImplTest {

    private LoginBlServImpl loginBlServImpl;



    @Before
    public void setup() {
        loginBlServImpl = new LoginBlServImpl();
    }

    @Test
    public void testLogin() {
        assertEquals(LoginResult.WRONG_ID,loginBlServImpl.login("wrong",""));
        assertEquals(LoginResult.WRONG_PASSWORD,loginBlServImpl.login("correct","wrong"));
        assertEquals(LoginResult.SUCCESS,loginBlServImpl.login("correct","correct"));
        assertEquals(LoginResult.WRONG_PASSWORD,loginBlServImpl.login("already","wrong"));
        assertEquals(LoginResult.ALREADY_LOGIN,loginBlServImpl.login("already","correct"));
    }

    @Test
    public void testGetInfo() {
        assertEquals(null,loginBlServImpl.getUserInfo(null));
        assertEquals(false,loginBlServImpl.getUserInfo("correct") == null);
    }

    @Test
    public void testLogout() {
        assertEquals(false,loginBlServImpl.logout("wrong"));
        //宸茬粡鐧诲綍鐨勬儏鍐�
        assertEquals(true,loginBlServImpl.logout("correct"));
        //鐧诲嚭涔嬪悗鍐嶆潵涓�娆�
        assertEquals(false,loginBlServImpl.logout("notLogin"));
    }
}
