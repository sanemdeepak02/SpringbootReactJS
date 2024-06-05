package com.hcm.accessingdatamysql.Service;

import java.io.Serial;
import java.lang.annotation.Retention;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcm.accessingdatamysql.entity.employee;
import com.hcm.accessingdatamysql.ThreadLocalEmployeeHolder;
import com.hcm.accessingdatamysql.Repository.employeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hcm.accessingdatamysql.ThreadLocalEmployeeHolder;

// @Embeddable
// @Transactional()
// @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)

// @Transactional(propagation = Propagation.REQUIRES_NEW) 
// @Transactional(isolation = Isolation.SERIALIZABLE) 
@Service
public class employeeService{

  @Autowired
  private employeeRepository empRepo;

  @SuppressWarnings("null")
  public employee saveEmpInfo(employee empSave) {
    return empRepo.save(empSave);
  }

  public List<employee> fetchAll() {
    return empRepo.fetchAllFromCustom();
  }

  public List<employee> getAnEmpbyBU(String buName) {
    // empRepo.@Setter(tcName="Done")
    return empRepo.getEmpByBU(buName);
  }

  // @SuppressWarnings("null")
  
  public List<employee> transToGetBUbyEmp(String bu) {

    List<employee> emp = empRepo.getEmpByBU(bu);
    if (emp.size() > 0) {
      emp.get(0).setReserve_By("Automation");
      System.out.println("This is set of array value....:" + emp.get(0));

    }
    empRepo.save(emp.get(0));
    return emp;
  }

  
  // @SuppressWarnings("null")
  // @Syncrenize
  @Transactional
  // @Transactional(isolation = Isolation.READ_COMMITTED) // did not work
  // @Transactional(isolation = Isolation.ISOLATION_SERIALIZABLE)
  // @Transactional(propagation = Propagation.REQUIRES_NEW)
  // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
  public synchronized  List<employee> transToGetBUbyEmp(String tcName, String tcStatus, String dataresrveBy, Integer envid,
      String bUnitValue, String reservedFlag) {


    // List<employee> emp = empRepo.getActEmpByBU(envid,bUnitValue);
    // // System.out.println("Thsi is employee record" + emp.get(0));
    // if (emp.size()>0) {
    // emp.get(0).setTc_Status(tcStatus);
    // emp.get(0).setTc_Name(tcName);
    // emp.get(0).setReserve_By(dataresrveBy);
    // //System.out.println("This is set of array value....:" + emp.get(0));

    // }
    // // if(reservedFlag=="yes"){
    // empRepo.save(emp.get(0));
    // // }

    // return emp;

    // Working Code..
    if (reservedFlag == "yes") {
      List<employee> emp = empRepo.getActEmpByBU(envid, bUnitValue);

      // ThreadLocalEmployeeHolder.setEmployee(emp);
        //return employee;

    //   // if (transToGetBUbyEmp.getVersion != emp.getVersion) 
    //   //     {
    //   //       System.out.print("Employee data has been modified by another transaction.");
    //   //     }


    //   if (emp.isEmpty()) {
    //     System.out.print("No record for this query..");
    //    // throw new RuntimeException("this is not an error, concurrent transaction error")
    // }

            // .orElseThrow(() new RuntimeException("Employee not found"));
      if (emp.size() > 0) {
        emp.get(0).setTc_Status(tcStatus);
        emp.get(0).setTc_Name(tcName);
        emp.get(0).setReserve_By(dataresrveBy);
        // System.out.println("This is set of array value....:" + emp.get(0));
      }
      System.out.print("Thread Name" + " ----------- " + Thread.currentThread().getName() +"Reserving (getEmpBUnit)data for Test name:.. " + tcName + " ----------- " + bUnitValue + " ----------- " + envid + " ----------- ");
      empRepo.save(emp.get(0));
      return emp;
    } else {
      System.out.print("Not Reserving (getEmpBUnit) data for the Test name:.. " + tcName );
      //System.out.print("This is thread name..... " + Thread.currentThread().getName() + " ----------- " + tcName );
      List<employee> emp = empRepo.getActEmpByBuNR(envid, bUnitValue);
      return emp;
    }

  }

  // @SuppressWarnings("null")
  // @Transactional()
  public List<employee> transToGetBUjobCode(Integer envid, String tcName, String tcStatus, String dataresrveBy,
      String bUnitValue, Integer jobCode, String reservedFlag) {

    // List<employee> emp = empRepo.getEmpByBUjobCode(envid,bUnitValue,jobCode);
    // // System.out.println("Thsi is employee record" + emp.get(0));
    // if (emp.size()>0) {
    // emp.get(0).setTc_Status(tcStatus);
    // emp.get(0).setTc_Name(tcName);
    // emp.get(0).setReserve_By(dataresrveBy);
    // //System.out.println("This is set of array value....:" + emp.get(0));

    // }
    // empRepo.save(emp.get(0));
    // return emp;

    // Working Code..
    if (reservedFlag == "yes") {
      List<employee> emp = empRepo.getEmpByBUjobCode(envid, bUnitValue, jobCode);
      if (emp.size() > 0) {
        emp.get(0).setTc_Status(tcStatus);
        emp.get(0).setTc_Name(tcName);
        emp.get(0).setReserve_By(dataresrveBy);
        // System.out.println("This is set of array value....:" + emp.get(0));
      }
      empRepo.save(emp.get(0));
      return emp;
    } else {
      System.out.print("This is getEmp Jobcode condition without reservation... ");
      List<employee> emp = empRepo.getEmpByBUjobCodeNR(envid, bUnitValue, jobCode);
      return emp;
    }
  }

  // @SuppressWarnings("null")
  // @Transactional()
  public List<employee> transToGetBUbyParams(Integer envid, String tcName, String tcStatus, String dataresrveBy,
      String bUnitValue, Integer jobCode, String colAgreementName, String reservedFlag) {

    // System.out.println("Thsi is employee record" + emp.get(0));

    // Working Code..
    if (reservedFlag == "yes") {
      List<employee> emp = empRepo.getEmpByBUConditions(envid, bUnitValue, jobCode, colAgreementName);
      if (emp.size() > 0) {
        emp.get(0).setTc_Status(tcStatus);
        emp.get(0).setTc_Name(tcName);
        emp.get(0).setReserve_By(dataresrveBy);
        // System.out.println("This is set of array value....:" + emp.get(0));
      }
      empRepo.save(emp.get(0));
      return emp;
    } else {
      System.out.print("This is getEmp BU condition location... ");
      List<employee> emp = empRepo.getEmpByBUCondition(envid, bUnitValue, jobCode, colAgreementName);
      return emp;
    }

  }

  // @SuppressWarnings("null")
  // @Transactional()
  public List<employee> transToGetBUcity(Integer envid, String tcName, String tcStatus, String dataresrveBy,
      String bUnitValue, String city, String reservedFlag) {

    // List<employee> emp = empRepo.getEmpByBUcity(envid,bUnitValue,city);
    // // System.out.println("Thsi is employee record" + emp.get(0));
    // if (emp.size()>0) {
    // emp.get(0).setTc_Status(tcStatus);
    // emp.get(0).setTc_Name(tcName);
    // emp.get(0).setReserve_By(dataresrveBy);
    // //System.out.println("This is set of array value....:" + emp.get(0));

    // }
    // empRepo.save(emp.get(0));
    // return emp;

    // Working Code..
    if (reservedFlag == "yes") {
      List<employee> emp = empRepo.getEmpByBUcity(envid, bUnitValue, city);
      if (emp.size() > 0) {
        emp.get(0).setTc_Status(tcStatus);
        emp.get(0).setTc_Name(tcName);
        emp.get(0).setReserve_By(dataresrveBy);
        // System.out.println("This is set of array value....:" + emp.get(0));
      }
      empRepo.save(emp.get(0));
      return emp;
    } else {
      System.out.print("This is get City without reservation... ");
      List<employee> emp = empRepo.getEmpByBUcityNR(envid, bUnitValue, city);
      return emp;
    }
  }

  // @SuppressWarnings("null")
  // @Transactional()
  public List<employee> transToGetBUbyVol(String tcName, String tcStatus, String dataresrveBy, Integer envid,
      String bUnitValue, Integer limit) {

    List<employee> emp = empRepo.getActEmpByNum(envid, bUnitValue, limit);
    System.out.println("Thsi is employee record" + emp.get(0));
    Integer recSize = emp.size();
    if (emp.size() > 0) {
      for (int i = 0; i < recSize; i++) {
        emp.get(i).setTc_Status(tcStatus);
        emp.get(i).setTc_Name(tcName);
        emp.get(i).setReserve_By(dataresrveBy);
      }

      // System.out.println("This is set of array value....:" + emp.get(0));

    }
    empRepo.save(emp.get(0));
    return emp;
  }

  // public List<employees> getEmployeeByBU(String TCName, String tcsStatus,
  // String ReservedBy, String envName, String asignStatus, String buName );

  // //New Addition for getting the active BU
  // @SuppressWarnings("null")
  // @Transactional()
  // public List<employees> transToGetActiveBU( String TCname, String tcsStatus,
  // String ReservedBy,Integer env_id, String asignStatus, String buName){
  // List<employees> emp = empRepo.getActEmpByBU(env_id,asignStatus,buName);

  // ****************** try out *****************
  // public List<employees> transToGetActiveBU( String TCname, String tcsStatus,
  // String ReservedBy,Integer env_id, String buName){
  // List<employees> emp = empRepo.getActEmpByBU(env_id,buName);
  // System.out.println(TCname + tcsStatus + ReservedBy + env_id + buName);
  // System.out.println("this is an array:.. "+emp);
  // if (emp.size()>0) {
  // emp.get(0).setTcName(TCname);
  // emp.get(0).setStatus(tcsStatus);
  // emp.get(0).setReservedBy(ReservedBy);
  // //System.out.println("This is set of array value....:" + emp.get(0));
  // empRepo.save(emp.get(0));
  // }
  // return empRepo.getActEmpByBU(env_id,buName);
  // }

  // public List<employees> transToGetActiveBU( Integer env_id, String buName){
  // List<employees> emp = empRepo.getActEmpByBU(env_id,buName);
  // System.out.println("envid & buname to be prented..: "+env_id + buName);
  // System.out.println("this is an array:.. "+emp);
  // if (emp.size()>0) {
  // // emp.get(0).setTcName(tcName);
  // emp.get(0).setStatus("In-Progress");
  // emp.get(0).setReservedBy("Automation");
  // //System.out.println("This is set of array value....:" + emp.get(0));
  // empRepo.save(emp.get(0));
  // }
  // return empRepo.getActEmpByBU(env_id,buName);
  // }

  // public List<employees> transToGetActiveBU(String tCname, String tcsStatus,
  // String reservedBy, Integer env_id,
  // String asignStatus, String buName) {
  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method
  // 'transToGetActiveBU'");
  // }

}
