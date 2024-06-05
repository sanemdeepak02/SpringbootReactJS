package com.hcm.accessingdatamysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hcm.accessingdatamysql.entity.Environment;
import com.hcm.accessingdatamysql.repository.environmentRepository;


@Service
public class environmentService {

    @Autowired
    private environmentRepository envRepo;

    @SuppressWarnings("null")
    public Environment saveEnvInfo(Environment envSave){
        return envRepo.save(envSave);
    }

    // public List<employee> fetchAll(){
    //     return empRepo.fetchAllFromCustom();
    // }

    public List<Environment> fetchEnv(){
      return envRepo.fetchAllEnvironment();
    }

  //   public List<Environment> getAllEnvironmentsWithEmployee() {
  //     return envRepo.findAllWithEmployee();
  // }


}
