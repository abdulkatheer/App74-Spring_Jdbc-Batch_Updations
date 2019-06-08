package org.katheer.dao;

import org.katheer.dto.Employee;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //PreparedStatement style
    @Override
    public int[] insert(List<Employee> employees) {
        int[] rowCounts = null;

        try {
            String query = "INSERT INTO employee VALUES(?,?,?,?)";
            rowCounts = jdbcTemplate.batchUpdate(query,
                    new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Employee employee = employees.get(i);
                    ps.setString(1, employee.getId());
                    ps.setString(2, employee.getName());
                    ps.setString(3, employee.getDept());
                    ps.setFloat(4, employee.getSalary());
                }

                @Override
                public int getBatchSize() {
                    return employees.size();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCounts;
    }

    //Statement style
    @Override
    public int[] update(String[] queries) {
        int[] rowCounts = null;

        try {
            rowCounts = jdbcTemplate.batchUpdate(queries);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCounts;
    }
}
