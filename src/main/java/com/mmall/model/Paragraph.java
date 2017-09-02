package com.mmall.model;

import java.util.Date;

public class Paragraph {
    private Integer id;

    private String name;

    private Integer videoid;

    private Integer sectionid;

    private Byte sort;

    private Date ctime;

    private Date mtime;

    private Integer cuser;

    private Integer muser;

    private Byte state;

    public Paragraph(Integer id, String name, Integer videoid, Integer sectionid, Byte sort, Date ctime, Date mtime, Integer cuser, Integer muser, Byte state) {
        this.id = id;
        this.name = name;
        this.videoid = videoid;
        this.sectionid = sectionid;
        this.sort = sort;
        this.ctime = ctime;
        this.mtime = mtime;
        this.cuser = cuser;
        this.muser = muser;
        this.state = state;
    }

    public Paragraph() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getVideoid() {
        return videoid;
    }

    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    public Integer getSectionid() {
        return sectionid;
    }

    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getCuser() {
        return cuser;
    }

    public void setCuser(Integer cuser) {
        this.cuser = cuser;
    }

    public Integer getMuser() {
        return muser;
    }

    public void setMuser(Integer muser) {
        this.muser = muser;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}