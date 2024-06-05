package com.hcm.accessingdatamysql.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcm.accessingdatamysql.Entity.employees;
import com.hcm.accessingdatamysql.Repository.employeeRepository;


@Service
public class employeeService {

    @Autowired
    private employeeRepository empRepo;

    @SuppressWarnings("null")
    public employees saveEmpInfo(employees empSave){
        return empRepo.save(empSave);
    }

    public List<employees> fetchAll(){
        return empRepo.fetchAllFromCustom();
    }

    // public List<employees> getByEmpName(String empname){
    //     // empRepo.set
    //     return empRepo.getEmpByFirstName(empname);
    // }

    public List<employees> getAnEmpbyBU(String buName){
        //empRepo.@Setter(tcName="Done")
        return empRepo.getEmpByBU(buName);
    }

    // /// New service
    // public List<employees> getEmpbyBUnit(String buName){    
    //     // empRepo.set
    //     return empRepo.getEmpByBU(buName);
    // }


    // public List<employees> getByEmpId(Integer empId){
    //     return empRepo.getEmpeById(empId);
    // }

    @SuppressWarnings("null")
    @Transactional()
   public List<employees> transToGetBUbyEmp( String bu){
      
      List<employees> emp = empRepo.getEmpByBU(bu);
    //   System.out.println("Thsi is employee record" + emp.get(0));
      if (emp.size()>0) {
         emp.get(0).setTc_Status("In-Progress");
         emp.get(0).setTc_Name("TC02");
         emp.get(0).setReserve_By("Automation");
       System.out.println("This is set of array value....:" + emp.get(0));
       
      }
      empRepo.save(emp.get(0));
      return emp;
   }

   //return empService.transToGetBUbyEmp(tc_Name,tc_Status,reserve_By,buName);


   @SuppressWarnings("null")
   @Transactional()
    public List<employees> transToGetBUbyEmp( String tcName, String tcStatus, String dataresrveBy, Integer envid, String bUnitValue){
     
     List<employees> emp = empRepo.getActEmpByBU(envid,bUnitValue);
   //   System.out.println("Thsi is employee record" + emp.get(0));
     if (emp.size()>0) {
        emp.get(0).setTc_Status(tcName);
        emp.get(0).setTc_Name(tcStatus);
        emp.get(0).setReserve_By(dataresrveBy);
      //System.out.println("This is set of array value....:" + emp.get(0));
      
     }
     empRepo.save(emp.get(0));
     return emp;
  }













       // public List<employees> getEmployeeByBU(String TCName, String tcsStatus, String ReservedBy, String envName, String asignStatus, String buName );

//    //New Addition for getting the active BU
//    @SuppressWarnings("null")
//    @Transactional()
//   public List<employees> transToGetActiveBU( String TCname, String tcsStatus, String ReservedBy,Integer env_id, String asignStatus, String buName){
//      List<employees> emp = empRepo.getActEmpByBU(env_id,asignStatus,buName);

// ****************** try out *****************
//     public List<employees> transToGetActiveBU( String TCname, String tcsStatus, String ReservedBy,Integer env_id, String buName){
//     List<employees> emp = empRepo.getActEmpByBU(env_id,buName);
//     System.out.println(TCname + tcsStatus + ReservedBy + env_id + buName);
//     System.out.println("this is an array:.. "+emp);
//      if (emp.size()>0) {
//         emp.get(0).setTcName(TCname);
//         emp.get(0).setStatus(tcsStatus);
//         emp.get(0).setReservedBy(ReservedBy);
//         //System.out.println("This is set of array value....:" + emp.get(0));
//         empRepo.save(emp.get(0));
//      }
//      return empRepo.getActEmpByBU(env_id,buName);
//   }


// public List<employees> transToGetActiveBU( Integer env_id, String buName){
//     List<employees> emp = empRepo.getActEmpByBU(env_id,buName);
//     System.out.println("envid & buname to be prented..:  "+env_id + buName);
//     System.out.println("this is an array:.. "+emp);
//      if (emp.size()>0) {
//         // emp.get(0).setTcName(tcName);
//        emp.get(0).setStatus("In-Progress");
//        emp.get(0).setReservedBy("Automation");
//         //System.out.println("This is set of array value....:" + emp.get(0));
//         empRepo.save(emp.get(0));
//      }
//      return empRepo.getActEmpByBU(env_id,buName);
//   }


// public List<employees> transToGetActiveBU(String tCname, String tcsStatus, String reservedBy, Integer env_id,
//         String asignStatus, String buName) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'transToGetActiveBU'");
// }



}
