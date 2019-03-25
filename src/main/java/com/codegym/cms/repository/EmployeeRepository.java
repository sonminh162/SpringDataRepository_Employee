package com.codegym.cms.repository;

import com.codegym.cms.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Page<Employee> findAllByDepartment(String department, Pageable pageable);

    Page<Employee> findAllByName(String name, Pageable pageable);

    Page<Employee> findAllByNameAndDepartmentId(String name,Long departmentId,Pageable pageable);
}
