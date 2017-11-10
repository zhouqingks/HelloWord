/*
 * 文件名：Phone1.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年10月31日
 */

package com.cheer.hibernate.bidirection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "tbl_phone1")
public class Phone1
{
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    
    @NaturalId
    @Column(name = "`number`", unique = true)
    private String number;
    
    @ManyToOne
    private Person1 person1;

    public Phone1()
    {
        
    }
    
    public Phone1(String number)
    {
        this.number = number;
    }


    public Phone1(String number, Person1 person1)
    {
        this(number);
        this.person1 = person1;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Person1 getPerson1()
    {
        return person1;
    }

    public void setPerson1(Person1 person1)
    {
        this.person1 = person1;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
    

   
    
    
    
}
