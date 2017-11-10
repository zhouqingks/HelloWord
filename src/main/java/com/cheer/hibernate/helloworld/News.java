package com.cheer.hibernate.helloworld;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Table(name = "tbl_news")
@Entity
public class News
{
    @Id
    @SequenceGenerator(name = "seq_id", sequenceName = "seq_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    public News()
    {

    }

    public News(Integer id, String title, String content)
    {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
    
    

    @Override
    public boolean equals(Object another)
    {
        
        return EqualsBuilder.reflectionEquals(this, another);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
