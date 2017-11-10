/*
 * 文件名：Employee.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.fetch.ex02;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.cheer.hibernate.BaseEntity;

@Table(name = "tbl_emp")
@Entity(name = "Employee")
public class Employee extends BaseEntity
{
    @NaturalId
    private String username;

    @Column(name = "pswd")
    private String password;

    private int accessLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Department department;
    
    @ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    public Employee()
    {}

    public Employee(String username, String password, int accessLevel)
    {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel)
    {
        this.accessLevel = accessLevel;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }
    
    
}
