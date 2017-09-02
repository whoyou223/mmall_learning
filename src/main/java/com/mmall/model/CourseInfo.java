package com.mmall.model;

public class CourseInfo {
    private Integer courseid;

    private String info;

    public CourseInfo(Integer courseid, String info) {
        this.courseid = courseid;
        this.info = info;
    }

    public CourseInfo() {
        super();
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}