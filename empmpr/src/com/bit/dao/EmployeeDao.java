package com.bit.dao;

import com.bit.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> findAll();

    /**
     * 查询指定编号的员工
     * @param empno
     * @return
     */
    public Employee findById(int empno);

    /**
     * 添加员工
     * @param emp
     * @return
     */
    public int add(Employee emp);

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    public int update(Employee emp);

    /**
     * 删除指定编号的员工
     * @param empno
     * @return
     */
    public int delete(int empno);
}
