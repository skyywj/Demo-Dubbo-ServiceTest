package basic.knowledge;

/**
 * Desc:lambda表达式练习
 *
 * @Author: CarryJey
 * @Date: 2018/9/30 13:46:16
 */
public class Lambda {

    /**
     * 参数传递是以Runnable的方式进行
     * @param a
     */
    public void test(Runnable a){
        a.run();
    }

    public static void main(String args[]) {

        Lambda lambda = new Lambda();

        lambda.test(
                () -> {
            System.out.println("a");
            System.out.println("b"); }
        );

        /**
         * 下面是另外两种写法
         */
//        Runnable r = () -> { System.out.println("lambda testing...");
//        System.out.println("test02");};
//        r.run();

//        new Thread(()->{System.out.println("a1");
//        System.out.println("a2");}).run();


    }
}
