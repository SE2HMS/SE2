package blTest;

/**
 * Created by Administrator on 2016/11/22.
 */

import static org.junit.Assert.*;
import book_bl_servImpl.OldBookBlServImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class OldBookBlServImplTest {
    private OldBookBlServImpl oldBookBlServImpl;
    private Method methods[];

    @Before
    public void setup() {
        oldBookBlServImpl = new OldBookBlServImpl();
        methods = OldBookBlServImpl.class.getDeclaredMethods();
    }

    @Test
    public void testGenerateId() {
        for(Method method:methods) {
            if(method.getName().equals("generateId")) {
                method.setAccessible(true);
                try {
                    String s = (String) method.invoke(oldBookBlServImpl);
                    assertEquals(16,s.length());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
