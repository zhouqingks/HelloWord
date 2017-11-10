/*
 * 文件名：fetchTest.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.test;

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

import com.cheer.hibernate.fetch.Department;
import com.cheer.hibernate.fetch.Employee;

public class fetchTest
{
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

        
        //获取事务并开始
        tx = session.beginTransaction();

    }
    
    @Test
    public void testSave()
    {
        Employee emp = new Employee("张三", new Department("研发部", "昆山"));
        
        session.save(emp);
        
        tx.commit();
    }
    
    @SuppressWarnings("unused")
    @Test
    public void testFind()
    {
        
        Employee emp = session.find(Employee.class, "402882f05f7b72e5015f7b72ec1e0000");
        
        tx.commit();
    }
    
    @Test
    public void testFind1()
    {
        String hql = "select e from Employee e where e.id = :id";
        Employee emp = session.createQuery(hql, Employee.class).setParameter("id", "402882f05f7b72e5015f7b72ec1e0000").getSingleResult();
        Assert.assertNotNull(emp.getDepartment().getName());
        tx.commit();
    }
}
