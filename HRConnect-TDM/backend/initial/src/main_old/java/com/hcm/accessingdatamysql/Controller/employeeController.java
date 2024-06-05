package com.hcm.accessingdatamysql.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hcm.accessingdatamysql.Entity.employees;
import com.hcm.accessingdatamysql.Service.employeeService;
import jakarta.transaction.Transactional;

@RestController
public class employeeController {

   @Autowired
   private employeeService empService;

   @PostMapping("/addEmp")
   public employees  postEmpDetails(@RequestBody employees empInfo){
    return empService.saveEmpInfo(empInfo);
   }

   @GetMapping("/all")
   public List<employees> fethController(){      
      return empService.fetchAll();
   }


   // *****************try out **************
   // // public List<employees> transToGetActiveBU(@PathVariable String TCname, @PathVariable String tcsStatus,@PathVariable String ReservedBy, @PathVariable String envName,@PathVariable String asignStatus, @PathVariable String buName){
   // // public List<employees> getEmployeeByBU(String TCName, String tcsStatus, String ReservedBy, String envName, String asignStatus, String buName );
   
   
   // @GetMapping(path="getBusinessUnit/{env_id}/{buName}")
   // public List<employees> getActiveEmp( @PathVariable Integer env_id, @PathVariable String buName){
   //    return empService.transToGetActiveBU(env_id,buName);
   // }

   // @GetMapping(path="getBusinessUnit/{TCname}/{tcsStatus}/{ReservedBy}/{env_id}/{buName}")
   // public List<employees> getActiveEmp(@PathVariable String TCname, @PathVariable String tcsStatus,@PathVariable String ReservedBy, @PathVariable Integer env_id, @PathVariable String buName){
   //    return empService.transToGetActiveBU(TCname,tcsStatus,ReservedBy,env_id,buName);
   // }

   //Working code for get via transaction for blocking data
   @GetMapping(path="getBUnit/{buName}")
   public List<employees> getActiveEmp(@PathVariable String buName){
      return empService.transToGetBUbyEmp(buName);
   }

   //Work in progress code:
   @GetMapping("getEmpBUnit/")
   
   public List<employees> getActiveBU(@RequestParam Map<String,String> multiParams){
      System.out.println(multiParams.values());
      List<String> list = new ArrayList<String>(multiParams.values());
      // System.out.println(list.get(0) + ".." + list.get(1) + "...." + list.get(2) + list.get(3) );
   
      // return empService.fetchAll();
       return empService.transToGetBUbyEmp(list.get(0),list.get(1),list.get(2),Integer.parseInt(list.get(3)),list.get(4));
   }


   ///// Working Code henc commeting : 3/27) 
   // @Transactional
   // @GetMapping(path="BunitName/{bu}")
   // public List<employees> fethActiveEmp(@PathVariable String bu){
      
   //    List<employees> emp = empService.getAnEmpbyBU(bu);
   //    if (emp.size()>0) {
   //       emp.get(0).setTc_Status("In-Progress");
   //       emp.get(0).setTc_Name("TC01");
   //       emp.get(0).setReserve_By("Automation");
   //     System.out.println("This is array 4 value....:" + emp.get(0));
  
   //    }
   //    empService.saveEmpInfo(emp.get(0));
   //    // return empService.getAnEmpbyBU(bu);
   //    return emp;
   // }



   //*************************** */

   // @PutMapping(path="Bunit/{buName}")
   // public List<employees> fethActiveEmpByUnit(@PathVariable String buName){
   //    // empService.
   //    List<employees> emp = empService.getAnEmpbyBU(buName);
   //     if (emp.size()>0) {
   //      System.out.println(emp);
   
   //     }
   //    return empService.getAnEmpbyBU(buName);
   // }


   // 	//<Newhire, benefits, absm, payroll>
	// @GetMapping(path="{env}/{id}")
   //  public @ResponseBody Optional<User> getUserid(@PathVariable("id") String id) {
   //      int pid = Integer.parseInt(id);
   //      return userRepository.findById(pid);
   //  }


   //***************************************** */

   // // @GetMapping("/{name:.*\\D.*}")
   // @RequestMapping(value = "name/{first_name}", method = RequestMethod.GET)
   // public List<employees> getEmpName(@PathVariable String first_name){
   //    //int get_id = Integer.parseInt(id);
   //    return empService.getByEmpName(first_name);
   // }

   // // @GetMapping("/{id:\\d+}")
   // @RequestMapping(value ="id/{employee_id}", method = RequestMethod.GET)
   // public List<employees> getEmployeeid(@PathVariable Integer employee_id){
   //    // int get_id = Integer.parseInt(employee_id);
   //    return empService.getByEmpId(employee_id);
   // }


//***************************************** */

// // Combined GET and POST method
   // @RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
   // public ResponseEntity<?> getProductOrModify(@PathVariable(required = false) String id, @RequestBody(required = false) Product product) {
   //    if (id != null) {
   //       int pid = Integer.parseInt(id);
   //       // GET request to fetch a specific product by ID
   //       employees fetchEmpbyID = (employees) empService.getByEmpId(id);
   //       if (fetchEmpbyID != null) {
   //             return new ResponseEntity<>(fetchEmpbyID, HttpStatus.OK);
   //       } else {
   //             return new ResponseEntity<>("Employee id not found", HttpStatus.NOT_FOUND);
   //       }
   //    } else if (product != null) {
   //       // POST request to create or update a product
   //       employees createdOrUpdatedProduct = empService.createOrUpdateEmployee(product);
   //       return new ResponseEntity<>(createdOrUpdatedProduct, HttpStatus.CREATED);
   //    } else {
   //       return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
   //    }
   // }

}
