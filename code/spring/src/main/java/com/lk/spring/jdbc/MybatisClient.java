package com.lk.spring.jdbc;

import com.lk.spring.business.mapper.UserDao;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * MyBatis 编程步骤
 * <p>
 * 创建 SqlSessionFactory 对象。
 * <p>
 * 通过 SqlSessionFactory 获取 SqlSession 对象。
 * <p>
 * 通过 SqlSession 获得 Mapper 代理对象。
 * <p>
 * 通过 Mapper 代理对象，执行数据库操作。
 * <p>
 * 执行成功，则使用 SqlSession 提交事务。
 * <p>
 * 执行失败，则使用 SqlSession 回滚事务。
 * <p>
 * 最终，关闭会话。
 * <p>
 */
public class MybatisClient {
    public static void main(String[] args) throws IOException {
        final Object user2 = getUser2("赖");
        System.out.println(user2);
        //$ sql注入
        final Object user3 = getUser3("'赖凯' or 1=1 ");
        System.out.println(user3);
    }

    public static Object getUser() {
        SqlSession sqlSession = null;
        Object data = null;
        try {
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryWithXML();
            sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            data = mapper.selectByPrimaryKey(1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return data;
    }

    public static Object getUser2(String name) {
        SqlSession sqlSession = null;
        Object data = null;
        try {
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryWithXML();
            sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            data = mapper.selectByName(name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return data;
    }
    public static Object getUser3(String name) {
        SqlSession sqlSession = null;
        Object data = null;
        try {
            SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryWithXML();
            sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            data = mapper.selectOneWithDollars(name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return data;
    }

    public static SqlSessionFactory getSqlSessionFactoryWithXML() throws IOException {
        //通过配置文件加载SqlSessionFactory
        String resource = "mybatis/mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        return sqlSessionFactory;
    }


    public static DataSource getDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC\n");
        properties.setProperty("username", "root");
        properties.setProperty("password", "root");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        return dataSource;
    }

    //使用java代码 构建
    public static SqlSessionFactory getSqlSessionFactoryWithCode() {
        DataSource dataSource = getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
