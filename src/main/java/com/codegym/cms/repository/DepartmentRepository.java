package com.codegym.cms.repository;

import com.codegym.cms.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    @Query("select d from Department d where d.name=?1")
    Department findByName(String deparmentName);
}
