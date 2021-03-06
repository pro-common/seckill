package org.seckill.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Created by wchb7 on 16-5-8.
 */

/**
 * 配置Spring和Junit整合,junit启动时加载springIOC容器
 * spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-basic.xml")
public class SeckillDaoTest {


    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }


    @Test
    public void testReduceNumber() throws Exception {
//      Java没有保存形参的记录:QueryAll(int offset,int limit)->QueryAll(arg0,arg1);
//      因为java形参的问题,多个基本类型参数的时候需要用@Param("seckillId")注解区分开来
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }


    @Test
    public void testQueryAll() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount:  " + updateCount);
    }


    @Test
    public void testGetConnection() {

        String url = "jdbc:mysql://127.0.0.1:3306/test_mysql";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String passwd = "root";

        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Get Connection failed!!!");
        }

        try {
            Connection con = DriverManager.getConnection(url, user, passwd);

            System.out.println("Get Connection Success!!!");

            Properties properties = con.getClientInfo();
            Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Object, Object> entry = it.next();
                System.out.println(entry.getKey() + "---------------" + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testComboPooledDataSource() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/test_mysql";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String passwd = "root";

        ComboPooledDataSource cpds = new ComboPooledDataSource();

        cpds.setDriverClass(driver);
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(passwd);

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(30);
        cpds.setMaxIdleTime(60);

        Connection con = cpds.getConnection();

        System.out.println("Get Connection Success!!!   " + con);

    }
}

