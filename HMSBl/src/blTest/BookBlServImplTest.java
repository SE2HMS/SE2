package blTest;

/**
 * Created by Administrator on 2016/11/22.
 */

import static org.junit.Assert.*;
import book_bl_servImpl.BookBlServImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class BookBlServImplTest {
    private BookBlServImpl bookBlServImpl;
    private Method methods[];

    @Before
    public void setup() {
        bookBlServImpl = new BookBlServImpl();
        methods = BookBlServImpl.class.getDeclaredMethods();
    }

    @Test
    public void testGenerateId() {
        for(Method method:methods) {
            if(method.getName().equals("generateId")) {
                method.setAccessible(true);
                try {
                    String s = (String) method.invoke(bookBlServImpl);
                    assertEquals(16,s.length());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
