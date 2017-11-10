/*
 * 文件名：aa.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月3日
 */

package com.cheer.hibernate.hql;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cheer.hibernate.BaseEntity;

@Table(name = "tbl_payment")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment extends BaseEntity
{

    private BigDecimal amount;

    private boolean completed;

    @ManyToOne
    private Person person;

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    
}