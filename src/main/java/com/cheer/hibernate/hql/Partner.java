/*
 * 文件名：b.java 版权：Copyright by www.cheer.com 描述： 修改人：皮皮周 修改时间：2017年11月3日
 */

package com.cheer.hibernate.hql;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import com.cheer.hibernate.BaseEntity;


@Table(name = "tbl_partner")
@Entity
public class Partner extends BaseEntity
{

    private String name;

    @Version
    private int version;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }


}
