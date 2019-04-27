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

    // Override behaviors of Member variable and Member function are different
    @Test
    public void test_method_override2() {
        A a = new A();
        B b = new B();

        Assert.assertEquals("A Key", a.getKey1());
        Assert.assertEquals("A Key", a.getKey2());

        Assert.assertEquals("B Key", b.getKey1());
        Assert.assertEquals("B Key", b.getKey2());
    }
}
