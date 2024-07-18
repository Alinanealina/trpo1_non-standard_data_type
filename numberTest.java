import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class numberTest {
    @Test
    public void test_number() throws Exception
    {
        number a = new number(12);
        ArrayList<Integer> e = new ArrayList<Integer>(Arrays.asList(2, 2, 3));
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
    }
    
    @Test
    public void test_op() throws Exception
    {
        number a = new number(12);
        a.sum(2);
        ArrayList<Integer> e = new ArrayList<Integer>(Arrays.asList(2, 7));
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
        
        a.diff(4);
        e = new ArrayList<Integer>(Arrays.asList(2, 5));
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
        
        a.mul(5);
        e = new ArrayList<Integer>(Arrays.asList(2, 5, 5));
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
        
        a.div(2);
        e = new ArrayList<Integer>(Arrays.asList(5, 5));
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
    }

    @Test
    public void test_set() throws Exception
    {
        number a = new number(12);
        ArrayList<Integer> e = new ArrayList<Integer>(Arrays.asList(2, 2, 5));
        a.set_ind_n(2, 5);
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
        
        a = new number(12);
        e = new ArrayList<Integer>(Arrays.asList(2, 2, 3, 5));
        a.set_n(5);
        assertArrayEquals(e.toArray(), a.get_arr().toArray());
        
        a = new number(12);
        e = new ArrayList<Integer>(Arrays.asList(2, 3));
        a.del_ind_n(1);
        assertArrayEquals(e.toArray(), a.get_arr().toArray());

        a.set_num(15);
        assertEquals(15, a.get_num());
    }
}
