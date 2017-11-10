/*
 * 文件名：Phone.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月1日
 */

package com.cheer.hibernate.one2one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_phone")
public class Phone
{
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    
    @Column
    private String type;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PhoneDetails phoneDetails;

    public Phone()
    {
        
    }

    public Phone(String type)
    {
        this.type = type;
    }

    public Phone(String type, PhoneDetails phoneDetails)
    {
        this(type);
        this.phoneDetails = phoneDetails;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public PhoneDetails getPhoneDetails()
    {
        return phoneDetails;
    }

    public void setPhoneDetails(PhoneDetails phoneDetails)
    {
        this.phoneDetails = phoneDetails;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
    
}
