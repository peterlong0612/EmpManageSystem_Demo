package com.bit.test;

import com.bit.dao.EmployeeDao;
import com.bit.dao.impl.EmployeeDaoImpl;
import com.bit.entity.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 前台
 */
public class Test {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("");
            System.out.println("********欢迎进入员工管理系统********");
            System.out.println("\t1.查询所有员工信息");
            System.out.println("\t2.查询指定编号员工");
            System.out.println("\t3.添加员工信息");
            System.out.println("\t4.修改员工信息");
            System.out.println("\t5.删除员工");
            System.out.println("\t6.退出");
            System.out.println("*****************************************");
            System.out.println("请选择菜单功能：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    findAll();
                    break;
                case 2:
                    findById();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    System.out.println("Thanks for using!");
                    return;
                default:
                    System.out.println("输入错误！");
            }
            System.out.println("请输入任意键继续");
//            scanner.next();   // 输入字符内容
            scanner.nextLine(); // 输入任意字符或者回车，但是输完功能选择之后的回车也会读进来
            scanner.nextLine();
        }while(true);
//        findAll();
//        findById();
//        add();
//        update();
//        delete();
    }

    private static void delete() {
        // 从键盘输入要修改的员工信息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除员工的编号：");
        int empno = sc.nextInt();

        // 调用后台完成添加操作并返回结果
//不用封装到员工对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        int n = employeeDao.delete(empno);
        // 输出结果
        if(n>0) {
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    public static void add(){
        // 从键盘输入要添加的员工信息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入员工姓名：");
        String ename = sc.next();
        System.out.println("请输入员工岗位：");
        String job = sc.next();
        System.out.println("请输入员工上级编号：");
        int mgr = sc.nextInt();
        System.out.println("请输入员工入职时间：（yyyy-MM-dd）");
        String sDate = sc.next(); //2021-03-01
        // 为什么添加的06-30变成了06-29？因为时区写成了UTC，mysql版本不同
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hireDate = null;
        try {
            hireDate = sdf.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //hireDate = java.sql.Date.valueOf(sdate); //只支持yyyy-mm-dd
        System.out.println("请输入员工薪水：");
        double sal = sc.nextDouble();
        System.out.println("请输入员工津贴：");
        double comm = sc.nextDouble();
        System.out.println("请输入员工部门编号：");
        int deptno = sc.nextInt();

        // 调用后台完成添加操作并返回结果
        Employee emp = new Employee(ename, job, mgr, hireDate, sal, comm, deptno);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        int n = employeeDao.add(emp);
        // 输出结果
        if(n>0) {
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
    public static void update(){
        // 从键盘输入要修改的员工信息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改员工的编号：");
        int empno = sc.nextInt();
        System.out.println("请输入员工岗位：");
        String job = sc.next();
        System.out.println("请输入员工薪水：");
        double sal = sc.nextDouble();
        System.out.println("请输入员工津贴：");
        double comm = sc.nextDouble();
        System.out.println("请输入员工部门编号：");
        int deptno = sc.nextInt();

        // 调用后台完成添加操作并返回结果
        Employee emp = new Employee(empno, job, sal, comm, deptno);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        int n = employeeDao.update(emp);
        // 输出结果
        if(n>0) {
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
    public static void findById(){
        // 键盘获取员工编号
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的员工编号:");
        int empno = scanner.nextInt();

        // 调用后台获取指定编号员工
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee emp = employeeDao.findById(empno);
        // 在前台输出指定编号员工
        if(emp != null){
            System.out.println("编号\t姓名\t岗位\t上级编号\t入职时间\t薪水补助\t所属部门编号");
            System.out.println(emp.getEmpno()+"\t"+emp.getEname()+"\t"+emp.getJob()+"\t"
                    +emp.getMgr()+"\t"+emp.getHireDate()+"\t"+emp.getSal()+"\t"
                    +emp.getComm()+"\t"+emp.getDeptno());
        }else{
            System.out.println("查无此人");
        }
    }
    /**
     * 查询所有员工的前台
     */
    public static void findAll(){
        // 调用后台获取员工列表
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        List<Employee> emplist = employeeDao.findAll();
        // 在前台输出员工列表
        System.out.println("编号\t姓名\t岗位\t上级编号\t入职时间\t薪水补助\t所属部门编号");
        for(Employee emp:emplist){
            System.out.println(emp.getEmpno()+"\t"+emp.getEname()+"\t"+emp.getJob()+"\t"
                    +emp.getMgr()+"\t"+emp.getHireDate()+"\t"+emp.getSal()+"\t"
                    +emp.getComm()+"\t"+emp.getDeptno());
        }
    }

    /**
     * 随机生成指定数量员工
     * @param num
     * @return
     */
    public static int emp_generator(int num){
        return 0;
    }
}
