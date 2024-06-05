package com.hcm.accessingdatamysql.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcm.accessingdatamysql.Entity.employees;
import com.hcm.accessingdatamysql.Service.employeeService;

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

   @GetMapping(path="BunitName/{bu}")
   public List<employees> fethActiveEmp(@PathVariable String bu){

      return empService.getAnEmpbyBU(bu);
   }


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
