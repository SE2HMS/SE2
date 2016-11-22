package blTest;

/**
 * Created by Administrator on 2016/11/22.
 */

import static org.junit.Assert.*;
import book_bl_servlmpl.BookBlServlmpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class BookBlServImplTest {
    private BookBlServlmpl bookBlServlmpl;
    private Method methods[];

    @Before
    public void setup() {
        bookBlServlmpl = new BookBlServlmpl();
        methods = BookBlServlmpl.class.getDeclaredMethods();
    }

    @Test
    public void testGenerateId() {
        for(Method method:methods) {
            if(method.getName().equals("generateId")) {
                method.setAccessible(true);
                try {
                    String s = (String) method.invoke(bookBlServlmpl);
                    assertEquals(16,s.length());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
