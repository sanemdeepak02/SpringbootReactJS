package com.hcm.accessingdatamysql.Repository;

import java.util.List;

import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcm.accessingdatamysql.Entity.employees;

public interface employeeRepository extends JpaRepository<employees,Integer> {

    @Query(value = "SELECT e from employees e")
    public List<employees> fetchAllFromCustom();

    // Assignment Status="Active - Payroll Eligible"
    // Business Unit Name="Hawaii"

    // @Query("SELECT n from employees n WHERE n.assgStatus='Active - Payroll Eligible' AND n.tcstatus<>'Blocked' AND n.buName = :buName ORDER BY n.buName DESC LIMIT 1")

    @Query("SELECT n from employees n WHERE n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName ORDER BY n.buName DESC LIMIT 1")
    public List<employees> getEmpByBU(String buName);


    // // This is join Table and new parameters details
    // // select * from environments  inner join employees on employees.env_id=environments.env_id where employees.env_id=1 limit 1;
    // @Query("SELECT n from employees n WHERE n.assgStatus='Active - Payroll Eligible' AND n.status<>'Blocked' AND n.envid=:env_id AND n.buName = :buName ORDER BY n.buName DESC LIMIT 1")
    // // public List<employees> getActEmpByBU( Integer env_id, String assgStatus, String buName );
    // public List<employees> getActEmpByBU(Integer env_id, String buName);


    // public List<employees> getEmployeeByBU(String TCName, String tcsStatus, String ReservedBy, String envName, String asignStatus, String buName );

    // public default List<employees> findByBU(String buString) {
    //     Object jdbcTemplate;
    //     // Use a native SQL query with "FOR UPDATE" to lock the record for update.
    //     return ((Object) jdbcTemplate).queryForObject(
    //         "SELECT * FROM product WHERE id = ? FOR UPDATE",
    //         new Object[]{buString},
    //         (rs, rowNum) -> new Product(
    //             rs.getInt("id"),
    //             rs.getString("name"),
    //             rs.getDouble("price")
    //         )
    //     );
    // }
}
