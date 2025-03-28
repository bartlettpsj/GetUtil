package com.iqss;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LodashGetTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LodashGetTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LodashGetTest.class );
    }


    /**
     *  Simple Single Test
     */

    public class Inner1 {
        String s = "hello";
        private Inner2 priv = new Inner2();;
        public Inner2 pub = new Inner2();
    }

    public class Inner2 {
        public Integer i = 123;
        private Integer i2 = 456;
        Integer[] i3 = {1, 2, 3};
        Inner3[] inner3 = {new Inner3(), new Inner3()};
    }

    public class Inner3 {
        public Integer j1 = 789;
        private Integer j2 = 786;
        Integer[] j3 = {6, 7, 8, 9};
    }

    public void testApp()
    {
        Inner1 inner1 = new Inner1();

        var s = LodashGet.get(inner1, "s");
        assertTrue(s.equals("hello"));

        Integer i = (Integer) LodashGet.get(inner1, "pub.i");
        assertEquals((Integer) 123, i);

        Integer i2 = (Integer) LodashGet.get(inner1, "priv.i2");
        assertTrue(456 == i2);

        Integer i3 = (Integer) LodashGet.get(inner1, "does.not.exist.i2");
        assertTrue(i3 == null);

        Integer i4 = (Integer) LodashGet.get(inner1, "priv.i3[1]");
        assertTrue(i4 == 2);

        Integer i5 = (Integer) LodashGet.get(inner1, "priv.inner3[1].j2");
        assertTrue(i5 == 786);
    }
}


