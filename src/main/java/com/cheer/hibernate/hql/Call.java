/*
 * 文件名：aa.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月3日
 */

package com.cheer.hibernate.hql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cheer.hibernate.BaseEntity;

@Entity
@Table(name = "phone_call")
public class Call extends BaseEntity
{

    @ManyToOne
    private Phone phone;

    @Column(name = "call_timestamp")
    private Date timestamp;

    private int duration;

    public Call()
    {
        
    }
    
    

    public Call(Date timestamp, int duration)
    {
        this.timestamp = timestamp;
        this.duration = duration;
    }



    public Phone getPhone()
    {
        return phone;
    }

    public void setPhone(Phone phone)
    {
        this.phone = phone;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }


}
