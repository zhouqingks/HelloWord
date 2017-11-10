/*
 * 文件名：Project.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月2日
 */

package com.cheer.hibernate.fetch.ex02;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cheer.hibernate.BaseEntity;

@Table(name = "tbl_project")
@Entity(name = "Project")
public class Project extends BaseEntity
{
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();
    
    public List<Employee> getEmployees()
    {
        return employees;
    }
    
    public void setEmployees(List<Employee> employees)
    {
        this.employees = employees;
    }
}
