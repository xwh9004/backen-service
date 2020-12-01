package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.entity.Result;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 13:42 on 2020/12/1
 * @version V0.1
 * @classNmae ProductController
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public Result saveProduct(@RequestBody Product product){
        productService.insert(product);
        return Result.buildSuccess();
    }
}
