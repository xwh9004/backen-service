package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p><b>Description:</b>
 *
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 17:50 on 2020/12/1
 * @version V0.1
 * @classNmae ProductServiceApplication
 */
@SpringBootApplication(scanBasePackageClasses =ProductServiceApplication.class )
public class ProductServiceApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext application = SpringApplication.run(ProductServiceApplication.class);

    }


}
