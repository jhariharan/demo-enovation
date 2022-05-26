package com.enovation.demoenovation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.enovation.demoenovation.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>, QueryByExampleExecutor<EmployeeRepository> {
}
