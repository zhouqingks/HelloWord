/*
 * 文件名：c.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：皮皮周
 * 修改时间：2017年11月3日
 */

package com.cheer.hibernate.hql;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import com.cheer.hibernate.BaseEntity;

@Table(name = "tbl_phone")
@Entity
public class Phone extends BaseEntity
{


    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>(  );

    @OneToMany(mappedBy = "phone")
    @MapKey(name = "timestamp")
    @MapKeyTemporal(TemporalType.TIMESTAMP )
    private Map<Date, Call> callHistory = new HashMap<>();

    @ElementCollection
    private List<Date> repairTimestamps = new ArrayList<>(  );

    

    public Phone(String number, PhoneType type)
    {
        this.number = number;
        this.type = type;
    }

    public Phone()
    {}

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public void setType(PhoneType type)
    {
        this.type = type;
    }

    public void setCalls(List<Call> calls)
    {
        this.calls = calls;
    }

    public Person getPerson()
    {
        return person;
    }

    public String getNumber()
    {
        return number;
    }

    public PhoneType getType()
    {
        return type;
    }

    public List<Call> getCalls()
    {
        return calls;
    }

    public Map<Date, Call> getCallHistory()
    {
        return callHistory;
    }

    public List<Date> getRepairTimestamps()
    {
        return repairTimestamps;
    }

    

}