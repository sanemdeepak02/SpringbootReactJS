package com.hcm.accessingdatamysql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// import org.hibernate.mapping.Map;

import org.springframework.web.bind.annotation.CrossOrigin;

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
import com.hcm.accessingdatamysql.entity.Environment;
import com.hcm.accessingdatamysql.service.environmentService;
import jakarta.transaction.Transactional;

@RestController
public class environmentController {

   @Autowired
   private environmentService envService;

   @CrossOrigin(origins = "http://localhost:3000")
   @GetMapping("/env")
   public List<Environment> fethEnvValues(){    
      return envService.fetchEnv();
   }

      

   // @GetMapping("/withEmployee")
   // public List<Environment> getEmpVal() {
   //     return  envService.getAllEnvironmentsWithEmployee();
   // }


}


