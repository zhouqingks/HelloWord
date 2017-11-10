/*
 * 文件名：Person.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年10月31日
 */

package com.cheer.hibernate.one2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_person")
public class Person
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    
    @Column
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phoneList = new ArrayList<>();

    public Person()
    {
        super();
    }

    public Person(String name, List<Phone> phoneList)
    {
        super();
        this.name = name;
        this.phoneList = phoneList;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Phone> getPhoneList()
    {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList)
    {
        this.phoneList = phoneList;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
}
