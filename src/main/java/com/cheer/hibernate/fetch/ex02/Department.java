/*
 * 文件名：Department.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.fetch.ex02;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cheer.hibernate.BaseEntity;

@Table(name = "tbl_dept")
@Entity(name = "Department")
public class Department extends BaseEntity
{
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

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

    public List<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(List<Employee> employees)
    {
        this.employees = employees;
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
