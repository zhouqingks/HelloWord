/*
 * 文件名：FetchTest3.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cheer.hibernate.fetch.ex03.Department;
import com.cheer.hibernate.fetch.ex03.Employee;

public class FetchTest3
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
        Department dept = new Department("测试部");

        Employee emp = new Employee("scott");

        emp.setDepartment(dept);

        dept.getEmployees().add(emp);

        this.session.save(dept);

        this.session.save(emp);

        tx.commit();
    }

    @Test
    public void testFind()
    {
        List<Department> departments = this.session.createQuery("select d from Department d",
            Department.class).getResultList();

        for (Department dept : departments)
        {
            for (Employee emp : dept.getEmployees())
            {
                System.out.println(emp.getUsername());
            }

        }

        // Assert.assertEquals(1, departments.size());
    }

    @Test
    public void testFind1()
    {
        List<Department> departments = this.session.createQuery(
            "select d " + "from Department d " + "where d.name like :token",
            Department.class).setParameter("token", "开发部%").getResultList();

        for (Department dept : departments)
        {
            for (Employee emp : dept.getEmployees())
            {
                System.out.println(emp.getUsername());
            }

        }
    }
    
    
    /*
     *     select
        department0_.id as id1_0_0_,
        department0_.name as name2_0_0_,
        employees1_.department_id as department_id3_1_1_,
        employees1_.id as id1_1_1_,
        employees1_.id as id1_1_2_,
        employees1_.department_id as department_id3_1_2_,
        employees1_.username as username2_1_2_ 
    from
        tbl_department department0_ 
    left outer join
        tbl_employee employees1_ 
            on department0_.id=employees1_.department_id 
    where
        department0_.id=?
     */
    @SuppressWarnings("unused")
    @Test
    public void testFind2()
    {
        Department department = this.session.find( Department.class, "402880e65f7d1603015f7d1607750000");
        
    }
}
