package com.example.order;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication(scanBasePackageClasses =OrderServiceApplication.class )
public class OrderServiceApplication {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext application = SpringApplication.run(OrderServiceApplication.class);
        DataSource primaryDataSource = application.getBean("primaryDataSource", DataSource.class);
        DataSource replicationDataSource = application.getBean("replicationDataSource", DataSource.class);

        OrderService orderService = application.getBean(OrderService.class);

        Order order =new Order();
//        order.setUserId(1);
//        order.setProductId(1);
//        order.setProductAmount(1);
//        order.setProductUnitPrice(88.90);
//        order.setOrderTotalPrice(88.90);
//        orderService.save(order);
        System.out.println(orderService.query(1));

    }
}
