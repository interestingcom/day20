package com.hz.test;

import com.hz.dao.ProductDao;
import com.hz.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test {

    private InputStream inputStream;
    private SqlSession sqlSession;
    private ProductDao productDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成字节文件输入流
        inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(inputStream);
        //3.获取SqlSession对象
        sqlSession=factory.openSession();
        //4.获取dao的代理对象
        productDao=sqlSession.getMapper(ProductDao.class);
    }
    @After//用于在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        inputStream.close();
    }

    /*
     * 测试查询所有
     * */
    @org.junit.Test
    public  void testFinAll() throws IOException {

        //5.执行查询所有方法
        List<Product> products=productDao.findAll();
        for (Product product :
               products ) {
            System.out.println(product);
        }

    }

    //测试保存方法
    @org.junit.Test
    public void testSave() throws IOException {
        Product product=new Product();
        product.setPid("77");
        product.setIs_hot(66);
        product.setMarket_price(44.0);
        product.setPdesc("ewewwe");
        product.setPflag("0");
        //5.执行保存方法
        productDao.saveProduct(product);
        System.out.println("保存操作之后:"+product);
    }

    //测试更新操作
    @org.junit.Test
    public void testUpdate() {
        Product product=new Product();
        product.setPid("1");
        product.setPname("hello");


        //5.执行保存方法
       productDao.updateProduct(product);
    }

    //测试删除操作
    @org.junit.Test
    public void testDelete() {

        //5.执行删除方法
        productDao.deleteProduct("50");
    }

    //测试查询一个方法
    @org.junit.Test
    public void testFindOne() {

        //5.执行查询一个方法
        Product product = productDao.findById("44");
        System.out.println(product);
    }

    //测试模糊查询操作
    @org.junit.Test
    public void testFindByName() {


        List<Product> products=productDao.findByName("王");
        for (Product product :
                products) {
            System.out.println(product);
        }
    }

    //测试查询总记录的条数
    @org.junit.Test
    public void testFindTotal() {

        //5.执行删除方法
        int total = productDao.findTotal();
        System.out.println(total);
    }

 /*   //测试使用QueryVo作为查询条件
    @org.junit.Test
    public void testFindByVo() {
        QueryVo queryVo=new QueryVo();
        User user=new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        List<User> users=userDao.findUserByVo(queryVo);
        for (User u :
                users) {
            System.out.println(u);
        }
    }*/
}
