package com.codegym.cms.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(){
    }

    public Employee(String name,int age,String address,Date birthday){
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        return String.format("Employee[id=%d,name='%s',age=%d,address='%s'],id,name,age,address");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
