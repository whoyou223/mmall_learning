package com.mmall.model;

import java.util.Date;

public class CourseCatagoryMapping {
    private Integer id;

    private Integer catagoryid;

    private Integer courseid;

    private Date ctime;

    private Date mtime;

    private Integer cuser;

    private Integer muser;

    private Byte state;

    public CourseCatagoryMapping(Integer id, Integer catagoryid, Integer courseid, Date ctime, Date mtime, Integer cuser, Integer muser, Byte state) {
        this.id = id;
        this.catagoryid = catagoryid;
        this.courseid = courseid;
        this.ctime = ctime;
        this.mtime = mtime;
        this.cuser = cuser;
        this.muser = muser;
        this.state = state;
    }

    public CourseCatagoryMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatagoryid() {
        return catagoryid;
    }

    public void setCatagoryid(Integer catagoryid) {
        this.catagoryid = catagoryid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
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