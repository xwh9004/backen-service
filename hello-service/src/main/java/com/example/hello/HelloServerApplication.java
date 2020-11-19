package com.example.hello;

/**
 * <p><b>Description:</b>
 *
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:50 on 2020/11/2
 * @version V0.1
 * @classNmae HelloServerApplication
 */
public class HelloServerApplication {

    public static void main(String[] args) throws Exception {

//        startSingle();

        startGroup();



    }

    public static void startSingle(){
        HelloServer server01 =new HelloServer(8801);
        try {
            server01.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startGroup() throws Exception {
        Thread t1 = new Thread(()->{
            HelloServer server01 =new HelloServer(8801);
            try {
                server01.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            HelloServer server =new HelloServer(8802);
            try {
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()->{
            HelloServer server =new HelloServer(8803);
            try {
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
