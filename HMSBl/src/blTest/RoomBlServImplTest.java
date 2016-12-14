package blTest;

/**
 * Created by Administrator on 2016/11/28.
 */

import PO.RoomPO;
import VO.RoomVO;
import org.junit.Before;
import org.junit.Test;
import room_bl_servImpl.RoomBlServImpl;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class RoomBlServImplTest {
    RoomBlServImpl roomBlServImpl;

    @Before
    private void init() {
        roomBlServImpl = new RoomBlServImpl();
    }

    @Test
    public void testParseRoomVO() {
        RoomPO po = new RoomPO("hotelname", "type", new int[]{0, 0, 0}, 0, 0,0);
        Method[] methods = roomBlServImpl.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("parseRoomVO")) {
                try {
                    RoomVO roomVO = (RoomVO) method.invoke(po);
                    assertEquals(null,roomVO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
