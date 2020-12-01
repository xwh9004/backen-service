package com.example.product.service.impl;


import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static com.example.product.sql.SqlStatements.INSERT_SQL;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:52 on 2020/11/2
 * @version V0.1
 * @classNmae HelloServiceImpl
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private JdbcTemplate jdbcTemplate;



    /**
     *     "INSERT INTO t_product
     *     (product_name,product_amount,
     *     product_img_url,product_price,
     *     status,product_desc,
     *     create_time,update_time)
     *     VALUES(?,?,null,?,0,null,?,?)";
     * @param product
     */
    public void insert(Product product) {
        log.info("insert product ={}",product);


            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
                    prepareStatement.setString(1,product.getName());
                    prepareStatement.setInt(2,10000);
                    long createTime = System.currentTimeMillis();
                    product.setCreateTime(createTime);
                    product.setUpdateTime(createTime);
                    prepareStatement.setDouble(3,product.getPrice());
                    prepareStatement.setLong(4,product.getCreateTime());
                    prepareStatement.setLong(5,product.getUpdateTime());
                    return prepareStatement;
                }
            });


    }

}
