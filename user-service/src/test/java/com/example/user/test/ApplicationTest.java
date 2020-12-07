package com.example.user.test;

import com.example.user.UserApplication;
import com.example.user.service.UserService;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:42 on 2020/11/19
 * @version V0.1
 * @classNmae ApplicationTest
 */
public class ApplicationTest {

    @Test
    public void jdbc_test(){

        ConfigurableApplicationContext application = SpringApplication.run(UserApplication.class);

        UserService userService = application.getBean(UserService.class);

        System.out.println(userService.select(1));

    }
    @Test
    public void test_insert_million_order() throws SQLException {

        int[] users = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] products = new int[]{1,2,3,4,5,6,7,8,9,10};
        double[] products_price = new double[]{1.1,2.2,3.3,4.3,5.6,6.6,7.9,8.1,9.0,10.1};
        String insert_order_sql = "insert into t_order_1(order_no,product_id,product_amount,product_unit_price, " +
                "order_total_price, user_id,status,order_desc,create_time,update_time) values(?,?,1,?,?,?,0,null,?,?)";


        ConfigurableApplicationContext application = SpringApplication.run(UserApplication.class);


        DataSource dataSource = application.getBean(DataSource.class);

        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statements = connection.prepareStatement(insert_order_sql);
        Random random =new Random();
        long totalTime = 0;
        for(int i=1;i<=10;i++){

            for (int j=0;j<100000;j++){
                statements.setString(1,UUID.randomUUID().toString());
                int productId = random.nextInt(10);
                statements.setInt(2,products[productId]);
                statements.setDouble(3,products_price[productId]);
                statements.setDouble(4,products_price[productId]);
                statements.setInt(5,users[random.nextInt(10)]);
                long createTime = System.currentTimeMillis();
                statements.setDouble(6,createTime);
                statements.setDouble(7,createTime);
                statements.addBatch();
            }
            long start = System.currentTimeMillis();

            statements.executeBatch();
            connection.commit();
            long perTimes=(System.currentTimeMillis()-start);
            totalTime+=perTimes;
            System.out.println("第 "+i+"次耗时："+perTimes);

        }
        System.out.println("总次耗时："+totalTime);
    }

    @Test
    public void writer_order_to_file() throws Exception {

        int[] users = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] products = new int[]{1,2,3,4,5,6,7,8,9,10};
        double[] products_price = new double[]{1.1,2.2,3.3,4.3,5.6,6.6,7.9,8.1,9.0,10.1};
        String insert_order_sql = "insert into t_order_1(order_no,product_id,product_amount,product_unit_price, " +
                "order_total_price, user_id,status,order_desc,create_time,update_time) values";

        File  orderFile = new File("C:/Users/Administrator/Desktop","order1.sql");
        if(!orderFile.exists()){
            orderFile.createNewFile();
        }
        OutputStream out = new FileOutputStream(orderFile);
        Random random =new Random();
        StringBuilder totalSql = new StringBuilder();
        for(int i=0;i<25;i++){
            StringBuilder sql = new StringBuilder();
            sql.append(insert_order_sql);
            //?,?,1,?,?,?,0,null,?,?
            for (int j=0;j<40000;j++){
                int productId = random.nextInt(10);
                long createTime = System.currentTimeMillis();

                sql.append("(")
                        .append("'"+UUID.randomUUID().toString()+"'").append(",")
                        .append(products[productId]).append(",")
                        .append(1).append(",")
                        .append(products_price[productId]).append(",")
                        .append(products_price[productId]).append(",")
                        .append( users[random.nextInt(10)]).append(",")
                        .append( "0").append(",")
                        .append( "null").append(",")
                        .append(createTime).append(",")
                        .append(createTime)
                        .append("),");
            }
            totalSql.append(sql.substring(0,sql.length()-1)).append(";").toString();
            totalSql.append("\n");
        }
        ;
        out.write(totalSql.substring(0,totalSql.length()-1).getBytes("UTF-8"));
//        System.out.println(sql.substring(0,sql.length()-1));
        System.out.println("写入完成");
    }

    @Test
    public void test_map(){

        TreeMap<Stern,Integer> map = new TreeMap<Stern,Integer>((obj1,obj2)->{

            return obj2.weight-obj1.weight;
        });
        map.put(Stern.MI_JING,10);
        map.put(Stern.DI_JING,20);
        map.put(Stern.GAN_JING,20);
        map.put(Stern.FU_GUAN,20);
        System.out.println(map.firstKey().desc);

    }


    static enum Stern{
        GAN_JING(6,"杆径"),
        MI_JING(5,"米径"),
        XIONG_JING(4,"胸径"),
        DI_JING( 3,"地径"),
        FU_GUAN( 2,"冠幅"),
        ZHU_GAO( 1,"株高");

        private int weight;
        private String desc;
        private Stern(int weight,String desc){
            this.weight =weight;
            this.desc=desc;
        }
    }


}
