package com.model;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 32, nullable = false, unique = true)
    private String name;

    @Column(length = 32, nullable = false, unique = true)
    private String code;

    @Column
    private String description;

    @Column(name = "parent_id", nullable = false)
    private long parentId = 0;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Category() {}

    public Category(String name, String code, String description, long parentId, Date createdAt, Date updatedAt) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreatedAt() {
        return createdAt;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdatedAt() {
        return updatedAt;
    }
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
