package org.katheer.test;

import org.katheer.dao.EmployeeDao;
import org.katheer.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("org" +
                "/katheer/resource/applicationContext.xml");
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");

        /*
        Employee employee1 = new Employee();
        employee1.setId("E-101");
        employee1.setName("AAA");
        employee1.setDept("Dev");
        employee1.setSalary(6000);

        Employee employee2 = new Employee();
        employee2.setId("E-102");
        employee2.setName("BBB");
        employee2.setDept("Testing");
        employee2.setSalary(7000);

        Employee employee3 = new Employee();
        employee3.setId("E-103");
        employee3.setName("CCC");
        employee3.setDept("Dev");
        employee3.setSalary(8000);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employeeDao.insert(employees);
        */

        String[] queries = new String[3];
        queries[0] = "INSERT INTO employee VALUES('E-104', 'DDD', 'Dev', 9000)";
        queries[1] = "UPDATE employee SET salary = salary + 250 WHERE salary " +
                "< 8000";
        queries[2] = "DELETE FROM employee WHERE id='E-102'";

        int[] rowCounts = employeeDao.update(queries);

        for (int rowCount : rowCounts) {
            System.out.println("Rows Affected : " + rowCount);
        }
    }
}
