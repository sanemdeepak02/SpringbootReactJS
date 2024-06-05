package com.hcm.accessingdatamysql.Repository;

import java.util.List;

import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcm.accessingdatamysql.Entity.employees;

public interface employeeRepository extends JpaRepository<employees,Integer> {

    @Query(value = "SELECT e from employees e")
    public List<employees> fetchAllFromCustom();

//     Assignment Status="Active - Payroll Eligible"
// Business Unit Name="Hawaii"

@Query("SELECT n from employees n WHERE n.assgStatus='Active - Payroll Eligible' AND n.buName = :buName ORDER BY n.buName DESC LIMIT 1")
public List<employees> getEmpByBU(String buName);

    // @Query("SELECT n from employees n WHERE n.firstname = :first_name")
    // public List<employees> getEmpByFirstName(String first_name);


    // @Query("SELECT i from employees i WHERE i.id = :employee_id")
    // public List<employees> getEmpeById(Integer employee_id);

    // @Query(value = "SELECT e from employees e where ")
    // public List<employees> fetchSingleEmp();
}
