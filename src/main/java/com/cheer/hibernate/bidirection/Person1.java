/*
 * 文件名：Person1.java 版权：Copyright by www.cheer.com 描述： 修改人：皮皮周 修改时间：2017年10月31日
 */

package com.cheer.hibernate.bidirection;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "tbl_person1")
public class Person1
{
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @OneToMany(mappedBy = "person1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone1> phones = new ArrayList<>();

    public Person1()
    {

    }

    public Person1(List<Phone1> phones)
    {
        this.phones = phones;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<Phone1> getPhones()
    {
        return phones;
    }

    public void setPhones(List<Phone1> phones)
    {
        this.phones = phones;
    }

    public void addPhone1(Phone1 phone1)
    {
        this.phones.add(phone1);
        phone1.setPerson1(this);
    }

    public void removePhone1(Phone1 phone1)
    {
        this.phones.remove(phone1);
        phone1.setPerson1(null);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
