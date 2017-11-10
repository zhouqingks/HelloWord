/*
 * 文件名：HqlTest.java 版权：Copyright by www.cheer.com 描述： 修改人：皮皮周 修改时间：2017年11月6日
 */

package com.cheer.hibernate.test;


import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cheer.hibernate.hql.AddressType;
import com.cheer.hibernate.hql.Call;
import com.cheer.hibernate.hql.CreditCardPayment;
import com.cheer.hibernate.hql.Payment;
import com.cheer.hibernate.hql.Person;
import com.cheer.hibernate.hql.Phone;
import com.cheer.hibernate.hql.PhoneType;


public class HqlTest
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
        Person person = new Person("李晨", "大黑牛", "北京", new Date());
        person.getAddresses().put(AddressType.HOME, "北京王府大街");
        person.getAddresses().put(AddressType.OFFICE, "三里屯优衣库");
        
        Phone phone = new Phone("13856892546", PhoneType.MOBILE);
        
     // 因为没有加级联关系
        phone.setPerson(person);
        
        Call call = new Call(new Date(), 2);
        call.setPhone(phone);
        
        // 在手机里产生通话记录
        phone.getCallHistory().put(call.getTimestamp(), call);
        phone.getCalls().add(call);
        
        person.getPhones().add(phone);
        
        Payment pay = new CreditCardPayment();
        pay.setAmount(BigDecimal.valueOf(250.4));
        pay.setCompleted(true);


        this.session.save(person);

        pay.setPerson(person);
        
        this.session.save(pay);
        
        this.tx.commit();
    }
    
    @Test
    public void testAdd()
    {
        Phone phone = new Phone("0512-58962356", PhoneType.LAND_LINE);
        
        Person person = this.session.find(Person.class, "2c9623f15f8f46b3015f8f46c05f0000");
        
        person.getPhones().add(phone);
        
        phone.setPerson(person);
        
        this.session.flush();
        
        this.tx.commit();
    }
}
