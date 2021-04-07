package com.bit.entity;


import java.util.Date;

/**
 * 员工实体类
 * Emp类对应emp表
 * Emp成员变量对应emp的各个字段
 * Emp类的一个对象对应emp表的一个记录
 */
public class Employee {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hireDate;
    private double sal;
    private double comm;
    private int deptno;

    // 快速生成，alt+insert，选择constructor构造方法
    public Employee() {
    }
    // 如果没有该构造方法，在前台选择自动生成，检查这里函数体是否为空
    public Employee(String ename, String job, int mgr,
                    Date hireDate, double sal, double comm, int deptno) {
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Employee(int empno, String ename, String job, int mgr,
                    Date hireDate, double sal, double comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Employee(int empno, String job, double sal, double comm, int deptno) {
        this.empno = empno;
        this.job = job;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    // 快速生成，alt+insert，选择getter and setter
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    // 快速生成，alt+insert，选择toString
    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }
}
