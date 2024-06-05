package com.hcm.accessingdatamysql.Repository;

import java.util.List;

import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcm.accessingdatamysql.entity.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
// import javax.persistence.LockModeType;
// import javax.persistence.QueryHint;
import java.util.Optional;

public interface employeeRepository extends JpaRepository<employee,Integer> {

    @Query(value = "SELECT e from employee e")
    public List<employee> fetchAllFromCustom();

    // Assignment Status="Active - Payroll Eligible"
    // Business Unit Name="Hawaii"

    // @Query("SELECT n from employee n WHERE n.assgStatus='Active - Payroll Eligible' AND n.tcstatus<>'Blocked' AND n.buName = :buName ORDER BY n.buName DESC LIMIT 1")

    @Query("SELECT n from employee n WHERE n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBU(String buName);

    // @Override
    //@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getActEmpByBU(Integer envid, String buName);

    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.buName=:buName ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getActEmpByBuNR(Integer envid, String buName);

    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName ORDER BY n.buName DESC LIMIT :limit")
    public List<employee> getActEmpByNum(Integer envid, String buName, Integer limit);

    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName AND n.jobCode=:jobCode AND n.colAgreementName=:colAgreementName ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBUConditions(Integer envid, String buName, Integer jobCode, String colAgreementName);

    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.buName=:buName AND n.jobCode=:jobCode AND n.colAgreementName=:colAgreementName ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBUCondition(Integer envid, String buName, Integer jobCode, String colAgreementName);
    
    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName AND n.jobCode=:jobCode ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBUjobCode(Integer envid, String buName, Integer jobCode);

    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.buName=:buName AND n.jobCode=:jobCode ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBUjobCodeNR(Integer envid, String buName, Integer jobCode);
    
    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.reserve_By is null AND n.buName=:buName AND n.city=:city ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBUcity(Integer envid, String buName, String city);
    
    @Query("SELECT n from employee n WHERE n.env_id=:envid AND n.assgStatus='Active - Payroll Eligible' AND n.buName=:buName AND n.city=:city ORDER BY n.buName DESC LIMIT 1")
    public List<employee> getEmpByBUcityNR(Integer envid, String buName, String city);

    // // This is join Table and new parameters details
    // // select * from environments  inner join employee on employee.env_id=environments.env_id where employee.env_id=1 limit 1;
    // @Query("SELECT n from employee n WHERE n.assgStatus='Active - Payroll Eligible' AND n.status<>'Blocked' AND n.envid=:env_id AND n.buName = :buName ORDER BY n.buName DESC LIMIT 1")
    // // public List<employee> getActEmpByBU( Integer env_id, String assgStatus, String buName );
    // public List<employee> getActEmpByBU(Integer env_id, String buName);


    // public List<employee> getEmployeeByBU(String TCName, String tcsStatus, String ReservedBy, String envName, String asignStatus, String buName );

    // public default List<employee> findByBU(String buString) {
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
