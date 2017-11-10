/*
 * 文件名：Department.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.fetch.ex01;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cheer.hibernate.BaseEntity;

@Entity
@Table(name = "tbl_dept")
public class Department extends BaseEntity
{
    @Column
    private String name;
    
    @Column
    private String address;

    public Department()
    {}

    public Department(String name, String address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    
    
}
