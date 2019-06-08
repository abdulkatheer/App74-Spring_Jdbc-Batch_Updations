package org.katheer.dao;

import org.katheer.dto.Employee;

import java.util.List;

public interface EmployeeDao {
    int[] insert(List<Employee> employees);
    int[] update(String[] queries);
}
