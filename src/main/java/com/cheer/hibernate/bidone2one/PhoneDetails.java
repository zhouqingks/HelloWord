/*
 * 文件名：PhoneDetails.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月1日
 */

package com.cheer.hibernate.bidone2one;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_phoneDetails")
public class PhoneDetails
{
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    
    @Column
    private String maker;

    @OneToOne(cascade = CascadeType.ALL)
    private Phone phone;
    

    public PhoneDetails()
    {
        
    }

    public PhoneDetails(String maker)
    {
        this.maker = maker;
    }
    
    public PhoneDetails(String maker, Phone phone)
    {
        this(maker);
        this.phone = phone;
    }

    public Phone getPhone()
    {
        return phone;
    }

    public void setPhone(Phone phone)
    {
        this.phone = phone;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMaker()
    {
        return maker;
    }

    public void setMaker(String maker)
    {
        this.maker = maker;
    }

    @Override
    public String toString()
    {    
        return ToStringBuilder.reflectionToString(this);
    }
    
    
    
    
}
