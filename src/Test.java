public class Test {

    public class Inner1 {
        String s = "hello";
        private Inner2 priv = new Inner2();;
        public Inner2 pub = new Inner2();

    }

    public static class Inner2 {
        public Integer i = 123;
        private Integer i2 = 456;
        Integer[] i3 = {1, 2, 3};
        Inner3[] inner3 = {new Inner3(), new Inner3()};
    }

    public static class Inner3 {
        public Integer j1 = 789;
        private Integer j2 = 786;
        Integer[] j3 = {6, 7, 8, 9};
    }

    public void start() {
        Inner1 inner1 = new Inner1();
        var s = GetUtil.get(inner1, "s");
        System.out.printf("s is: %s\n", s);

        Integer i = (Integer) GetUtil.get(inner1, "pub.i");
        System.out.printf("i is: %d\n", i);
        assert i == 123;

        Integer i2 = (Integer) GetUtil.get(inner1, "priv.i2");
        System.out.printf("i2 is: %d\n", i2);
        assert i2 == 456;

        Integer i3 = (Integer) GetUtil.get(inner1, "does.not.exist.i2");
        System.out.printf("i3 is: %d\n", i3);
        assert i3 == null;

        Integer i4 = (Integer) GetUtil.get(inner1, "priv.i3[1]");
        System.out.printf("i4 is: %d\n", i4);
        assert i4 == 2;

        Integer i5 = (Integer) GetUtil.get(inner1, "priv.inner3[1].j2");
        System.out.printf("i5 is: %d\n", i5);
        assert i5 == 786;


    }

    public static void main(String[] args) {
        Test test = new Test();
        test.start();
    }
}
