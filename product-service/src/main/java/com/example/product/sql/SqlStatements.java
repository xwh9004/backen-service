package com.example.product.sql;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:39 on 2020/11/19
 * @version V0.1
 * @classNmae SqlStatements
 */
public interface SqlStatements {

    final String INSERT_SQL = "INSERT INTO t_product (product_name,product_amount,product_img_url,product_price,status,product_desc,create_time,update_time) VALUES(?,?,null,?,0,null,?,?)";


}
