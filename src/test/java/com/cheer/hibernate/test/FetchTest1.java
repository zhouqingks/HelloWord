/*
 * 文件名：FetchTest1.java 版权：Copyright by www.cheer.com 描述： 修改人：皮皮周 修改时间：2017年11月2日
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

import com.cheer.hibernate.fetch.ex01.Department;
import com.cheer.hibernate.fetch.ex01.Employee;


public class FetchTest1
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

        // 获取事务并开始
        tx = session.beginTransaction();

    }

    @Test
    public void testSave()
    {
        Department dept = new Department("开发部", "昆山");

        Employee emp = new Employee("scott");

        emp.setDepartment(dept);

        this.session.save(emp);

        tx.commit();
    }

    @Test
    public void testFind()
    {
        Employee emp = this.session.find(Employee.class, "402880e65f7ccfdd015f7ccfe22c0000");
        Assert.assertNotNull(emp);
        Department dept = emp.getDepartment();

        Assert.assertNotNull(dept.getAddress());
    }
    
    @Test
    public void testFind1()
    {
        String hql = "select e from Employee e where e.username = :username";
        Employee emp = this.session.createQuery(hql, Employee.class).setParameter("username", "scott").getSingleResult();
        
        Assert.assertNotNull(emp);
    }
}
