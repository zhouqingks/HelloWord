/*
 * 文件名：BidOne2OneTest.java 版权：Copyright by www.cheer.com 描述： 修改人：皮皮周 修改时间：2017年11月1日
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

import com.cheer.hibernate.bidone2one.Phone;
import com.cheer.hibernate.bidone2one.PhoneDetails;
import com.cheer.hibernate.many2many.Address;
import com.cheer.hibernate.many2many.Person;


public class BidOne2OneTest
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
    public void testOne2One()
    {
        PhoneDetails phoneDetails = new PhoneDetails("小米");
        Phone phone = new Phone("小米5", phoneDetails);
        phoneDetails.setPhone(phone);

        session.save(phone);

        tx.commit();

        Assert.assertNotNull(phone.getPhoneDetails().getPhone());

    }

    @Test
    public void testMany2Many()
    {
        Address address1 = new Address("城南");
        Address address2 = new Address("城北");
        Person person1 = new Person();
        Person person2 = new Person();
        person1.getAddressList().add(address1);
        person1.getAddressList().add(address2);
        person2.getAddressList().add(address1);
        session.persist(person1);
        session.persist(person2);
        session.flush();
        
        person1.getAddressList().remove(address1);
        
        session.remove(person1);
        tx.commit();
    }
}
