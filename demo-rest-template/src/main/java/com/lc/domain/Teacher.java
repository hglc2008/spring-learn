package com.lc.domain;

/**
 * @author liuci
 * @date 2017/12/11
 * @desc
 */
public class Teacher {

    public Teacher(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    private String id;

    private String name;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
