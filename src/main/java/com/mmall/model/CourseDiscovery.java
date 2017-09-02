package com.mmall.model;

import java.util.Date;

public class CourseDiscovery {
    private Long id;

    private Long catagoryid;

    private Long courseid;

    private Integer sort;

    private Date ctime;

    private Date mtime;

    private Integer cuser;

    private Integer muser;

    private Byte state;

    public CourseDiscovery(Long id, Long catagoryid, Long courseid, Integer sort, Date ctime, Date mtime, Integer cuser, Integer muser, Byte state) {
        this.id = id;
        this.catagoryid = catagoryid;
        this.courseid = courseid;
        this.sort = sort;
        this.ctime = ctime;
        this.mtime = mtime;
        this.cuser = cuser;
        this.muser = muser;
        this.state = state;
    }

    public CourseDiscovery() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatagoryid() {
        return catagoryid;
    }

    public void setCatagoryid(Long catagoryid) {
        this.catagoryid = catagoryid;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
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