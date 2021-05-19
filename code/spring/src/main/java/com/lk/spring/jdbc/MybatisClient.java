package com.lk.spring.jdbc;

import com.lk.spring.generator.entity.User;
import com.lk.spring.business.mapper.UserDao;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

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
    //batchAdd();
//    final Object user = getUser();
//    System.out.println(user);
//    final Object user2 = getUser2("赖");
//    System.out.println(user2);
//    //$ sql注入
//    final Object user3 = getUser3("'赖凯' or 1=1 ");
//    System.out.println(user3);
    System.out.println(add());
  }

  public static int add() {
    SqlSession sqlSession = null;
    int id = 0;
    try {
      SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryWithXML();
      sqlSession = sqlSessionFactory.openSession();
      UserDao mapper = sqlSession.getMapper(UserDao.class);
      User user;
      //批量保存执行前时间
      user = new User();
      user.setName("赖凯i");
      mapper.insert(user);

      id = user.getId();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
    return id;
  }

  public static void batchAdd() {
    SqlSession sqlSession = null;
    try {
      SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryWithXML();
      sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
      UserDao mapper = sqlSession.getMapper(UserDao.class);
      User user;
      //批量保存执行前时间
      long start = System.currentTimeMillis();
      for (int i = 0; i < 1000; i++) {
        user = new User();
        user.setName("赖凯" + i);
        mapper.insert(user);
      }
      sqlSession.commit();
      long end = System.currentTimeMillis();
      //批量保存执行后的时间
      System.out.println("执行时长" + (end - start));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (sqlSession != null) {
        sqlSession.close();
      }
    }

  }

  public static Object getUser() {
    SqlSession sqlSession = null;
    Object data = null;
    try {
      SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryWithXML();
      sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
      data = sqlSession
          .selectList("com.lk.spring.business.mapper.UserDao.selectAll");

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
    properties.setProperty("url",
        "jdbc:mysql://112.124.23.142:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC\n");
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
