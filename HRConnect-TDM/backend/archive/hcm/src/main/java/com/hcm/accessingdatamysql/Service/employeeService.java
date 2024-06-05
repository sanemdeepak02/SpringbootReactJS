package com.hcm.accessingdatamysql.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcm.accessingdatamysql.Entity.employees;
import com.hcm.accessingdatamysql.Repository.employeeRepository;

@Service
public class employeeService {

    @Autowired
    private employeeRepository empRepo;

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
        // empRepo.set
        return empRepo.getEmpByBU(buName);
    }

    // public List<employees> getByEmpId(Integer empId){
    //     return empRepo.getEmpeById(empId);
    // }


}
