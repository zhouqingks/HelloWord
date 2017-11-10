/*
 * 文件名：FetchTest2.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

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

import com.cheer.hibernate.fetch.ex02.Department;
import com.cheer.hibernate.fetch.ex02.Employee;
import com.cheer.hibernate.fetch.ex02.Project;

public class FetchTest2
{
    private static final Logger LOGGER = LogManager.getLogger(FetchTest2.class);
    
    private static SessionFactory sf;

    private Session session;

    private Transaction tx;

    // 单个测试类只执行一次
    @BeforeClass
    public static void init()
    {
        // 创建SessionFactory
        sf = new MetadataSources(new StandardServiceRegistryBuilder().configure(
            "hibernate.cfg.xml").build()).buildMetadata().buildSessionFactory();
    }

    // 单个测试类只执行一次
    @AfterClass
    public static void destory()
    {
        // 关闭SessionFactory
        sf.close();
    }

    // 执行每个测试方法前都会执行
    @Before
    public void before()
    {
        // 获取Session（注：此时的session不需要关闭）
        this.session = sf.getCurrentSession();

        // 获取并开启事务
        tx = this.session.beginTransaction();

        LOGGER.info("Hibernate中session此时的flush状态是：{}", session.getHibernateFlushMode());

        // 设置flush的刷新时间点：默认是FlushMode.ALWAYS
        // this.session.setHibernateFlushMode(FlushMode.MANUAL);
    }

    @Test
    public void testSave()
    {
        Department dept = new Department("开发部", "昆山");

        Employee emp = new Employee("scott", "tiger", 1);
        
        emp.setDepartment(dept);
        
        Project project = new Project();

        project.getEmployees().add(emp);
        emp.getProjects().add(project);
        
        
        this.session.save(emp);

        tx.commit();
    }

    @Test
    public void testFind()
    {
        // 注意 load/get/find方法的区别
        // load查询到的是代理对象，并没有发送sql语句
        Employee emp = this.session.find(Employee.class, "402880e65f7d03bd015f7d03c1980000");
        
        Assert.assertNotNull(emp);
        
        Department dept = emp.getDepartment();
        
        Assert.assertNotNull(dept.getAddress());
    }

    @Test
    public void testFind1()
    {
        // JPQL/HQL
        String hql = "select e from Employee e where e.id = :id";
        
        // 单表查询 主表和副表分别查询
        Employee emp = this.session.createQuery(hql, Employee.class).setParameter("id", "402880e65f7d03bd015f7d03c1980000").getSingleResult();
    
        Assert.assertNotNull(emp);
    }
}
