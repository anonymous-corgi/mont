package notes.java.override;

import org.junit.Assert;
import org.junit.Test;

public class OverrideTest {


    // Override behaviors of Member variable and Member function are different
    @Test
    public void test_method_override() {
        B b = new B();

        Assert.assertEquals("A", b.getName1());
        Assert.assertEquals("B", b.getName2());
        Assert.assertEquals("B", b.name);


        A a = b;

        Assert.assertEquals("A", a.getName1());
        Assert.assertEquals("B", a.getName2());
        Assert.assertEquals("A", a.name);
    }
}
