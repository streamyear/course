package com.streamyear.course.excelModel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class UserExcelModel extends BaseRowModel {
    @ExcelProperty(index = 0,value = "编号")
    private Long id;

    @ExcelProperty(index = 1,value = "用户名")
    private String userName;

    @ExcelProperty(index = 2,value = "密码")
    private String password;

    @ExcelProperty(index = 3,value = "姓名")
    private String name;

    @ExcelProperty(index = 4,value = "年龄")
    private Integer age;

    @ExcelProperty(index = 5,value = "性别")
    private Boolean sex;

    @ExcelProperty(index = 6,value = "生日")
    private Date birthday;

    @ExcelProperty(index = 7,value = "创建时间")
    private Date created;

    @ExcelProperty(index = 8,value = "更新时间")
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}