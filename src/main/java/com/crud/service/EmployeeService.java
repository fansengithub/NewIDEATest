package com.crud.service;

import com.crud.bean.Employee;
import com.crud.bean.EmployeeExample;
import com.crud.bean.EmployeeExample.Criteria;


import com.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fansen on 2018/1/25.
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 批量删除
     */

    public  void  deleteBatch(List<Integer> ids){

        EmployeeExample employeeExample = new EmployeeExample();
        Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }

    /**
     *
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll(){

        return  employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 员工保存方法
     */
    public  void  saveEmp(Employee employee){
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检验用户名是否可用
     *
     * @param empName
     * @return  true：代表当前姓名可用   fasle：不可用
     */
    public boolean checkUser(String empName) {
        // TODO Auto-generated method stub
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    public Employee getEmp(Integer id){

        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return  employee;
    }

    /**
     * 员工更新
     */
        public  void  updateEmp(Employee employee){

            employeeMapper.updateByPrimaryKeySelective(employee);
        }

    /**
     *
     * 员工删除：
     */
    public void  deleteEmp(Integer id){
        employeeMapper.deleteByPrimaryKey(id);
    }
}
