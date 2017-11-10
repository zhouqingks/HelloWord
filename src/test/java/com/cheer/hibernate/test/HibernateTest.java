package com.cheer.hibernate.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cheer.hibernate.bidirection.Person1;
import com.cheer.hibernate.bidirection.Phone1;
import com.cheer.hibernate.helloworld.News;
import com.cheer.hibernate.many2one.Customer;
import com.cheer.hibernate.many2one.Order;
import com.cheer.hibernate.one2many.Person;
import com.cheer.hibernate.one2many.Phone;


public class HibernateTest
{
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger(HibernateTest.class);

    private static SessionFactory sf;

    private Session session;

    private Transaction tx;

    @BeforeClass
    public static void init()
    {
        // 创建SessionFactory
        sf = new MetadataSources(new StandardServiceRegistryBuilder().configure(
            "hibernate.cfg.xml").build()).buildMetadata().buildSessionFactory();
    }

    @AfterClass
    public static void destory()
    {
        // 关闭工厂
        sf.close();
    }

    @Before
    public void before()
    {
        // 获取Session
        session = sf.getCurrentSession();

        // 获取事务
        tx = session.getTransaction();
        //获取事务并开始
        //tx = session.beginTransaction();

    }
    

    @Test
    public void testSave()
    {

        // 开始事务
        tx.begin();

        News news = new News();

        news.setTitle("中共十九大常委公布");
        news.setContent("分別有：习近平、李克强、栗战书、汪洋、王沪宁、赵乐际、韩正。");

        // 插入到数据库里面
        session.persist(news);
        
        // 事务提交
        tx.commit();
    }

    @Test
    public void testQuery()
    {
        tx.begin();

        // 查询第一条数据
        News news = session.get(News.class, 2);
        News news1 = session.get(News.class, 2);
        Assert.assertNotNull(news);
        Assert.assertEquals(true, news == news1);
        // 事务提交
        tx.commit();
    }

    @Test
    public void testDelete()
    {
        // 开始事务
        tx.begin();

        // 删除第一条数据

        session.delete(session.get(News.class, 1));

        // 事务提交
        tx.commit();
    }

    @Test
    public void testUpdate()
    {
        // 开始事务
        tx.begin();

        // 更新第一条数据
        News news = new News(2, "中共十九大常委公布", "分別有：习近平、李克强、栗战书、汪洋、王沪宁、赵乐际、韩正。");
        session.update(news);
        

        // 事务提交
        tx.commit();
    }
    
    @Test
    public void testMany2One()
    {
        // 开始事务
        tx.begin();

       Order order = new Order("30", new Customer("库里"));
       session.save(order);
        

        // 事务提交
        tx.commit();
    }
    
    @Test
    public void testOne2Many()
    {
        // 开始事务
        tx.begin();

        Person person = new Person();
        person.setName("雷布斯");
        person.getPhoneList().add(new Phone("15715557426"));
        person.getPhoneList().add(new Phone("15715555321"));
        
        this.session.save(person);
        

        // 事务提交
        tx.commit();
    }
    
    @Test
    public void testBidirection()
    {
        // 开始事务
        tx.begin();

        Person1 person1 = new Person1();
        Phone1 phone1 = new Phone1("15715557426");
        Phone1 phone2 = new Phone1("15715555789");
        
        person1.addPhone1(phone1);
        person1.addPhone1(phone2);
        
        session.save(person1);
        session.flush();
        
        person1.removePhone1(phone1);

        // 事务提交
        tx.commit();
    }
    
}
