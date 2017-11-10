/*
 * 文件名：Employee.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.fetch.ex03;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.cheer.hibernate.BaseEntity;

@Entity
@Table(name = "tbl_emp")
public class Employee extends BaseEntity
{
    @NaturalId
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    public Employee()
    {}

    public Employee(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }
    
    
}
