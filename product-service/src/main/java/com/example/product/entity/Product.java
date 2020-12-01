package com.example.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:38 on 2020/12/1
 * @version V0.1
 * @classNmae Product
 */
@Data
@NoArgsConstructor
public class Product {


    private int id;
    private String name;
    private int amount;
    private String productImgUrl;
    private double price;
    private int  status;
    private String productDesc;
    private long createTime;
    private long updateTime;

}
