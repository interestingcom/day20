package com.hz.dao;

import com.hz.domain.Product;

import java.util.List;

public interface ProductDao {



    List<Product> findAll();

    void saveProduct(Product product);


    void updateProduct(Product product);


    void deleteProduct(String  productId);


    Product findById(String  productId);


    List<Product> findByName(String prdouctName);


    int findTotal();

 //   List<User> findUserByVo(QueryVo vo);
}
