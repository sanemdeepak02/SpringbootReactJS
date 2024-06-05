package com.hcm.accessingdatamysql.repository;

import java.util.List;

import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// import com.hcm.accessingdatamysql.entity.employee;
import com.hcm.accessingdatamysql.entity.Environment;
// import com.hcm.accessingdatamysql.entity.employee;

public interface environmentRepository extends JpaRepository<Environment,Integer> {

    @Query(value = "SELECT * from environment", nativeQuery = true)
    public List<Environment> fetchAllEnvironment();

    // @Query("SELECT e FROM Environment e JOIN FETCH e.employee")
    // List<Environment> findAllWithEmployee();

   
}
