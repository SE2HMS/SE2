package blTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import room_bl_serv.RoomBlServ;
import room_bl_servImpl.RoomBlServImpl;

/**
 * Created by Administrator on 2016/11/28.
 */
public class RoomBlServTest {
    private RoomBlServ roomBlServ;

    @Before
    private void initialize() {
        roomBlServ = new RoomBlServImpl();
    }

    @Test
    public void testGetRoom() {
        assertEquals(null,roomBlServ.getRoomInfo("wrong","wrong"));
        assertEquals(false,roomBlServ.getRoomInfo("correct","correct") == null);
        assertEquals(null,roomBlServ.getRoomInfo("correct","wrong"));
        assertEquals(null,roomBlServ.getRoomInfo("wrong","correct"));
    }
}
