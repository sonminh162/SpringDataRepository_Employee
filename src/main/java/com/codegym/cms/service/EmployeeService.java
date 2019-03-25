package com.codegym.cms.service;

import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Iterable<Employee> findAll();

    Employee findById(Long id);

    void save(Employee employee);

    void remove(Long id);

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllByDepartment(String department, Pageable pageable);

    Page<Employee> findAllByName(String name,Pageable pageable);

    Page<Employee> findAllByNameAndDepartment(String name,Long departmentId, Pageable pageable);
}
