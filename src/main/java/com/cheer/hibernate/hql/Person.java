/*
 * 文件名：d.java 版权：Copyright by www.cheer.com 描述： 修改人：皮皮周 修改时间：2017年11月3日
 */

package com.cheer.hibernate.hql;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.cheer.hibernate.BaseEntity;


@Table(name = "tbl_person")
@Entity
public class Person extends BaseEntity
{

    private String name;

    private String nickName;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderColumn(name = "order_id")
    private List<Phone> phones = new ArrayList<>();

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<AddressType, String> addresses = new HashMap<>();

    @Version
    private int version;

    public Person()
    {}

    public Person(String name, String nickName, String address, Date createdOn)
    {
        this.name = name;
        this.nickName = nickName;
        this.address = address;
        this.createdOn = createdOn;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public String getName()
    {
        return name;
    }

    public String getNickName()
    {
        return nickName;
    }

    public String getAddress()
    {
        return address;
    }

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public List<Phone> getPhones()
    {
        return phones;
    }

    public Map<AddressType, String> getAddresses()
    {
        return addresses;
    }

    public int getVersion()
    {
        return version;
    }

}