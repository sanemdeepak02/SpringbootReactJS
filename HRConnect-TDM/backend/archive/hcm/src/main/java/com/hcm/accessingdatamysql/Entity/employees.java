package com.hcm.accessingdatamysql.Entity;

import org.apache.catalina.Manager;
import org.aspectj.apache.bcel.classfile.Code;
import org.hibernate.sql.ast.tree.update.Assignment;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.annotation.Scheduled;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor

public class employees {

    //private static final String Env_ID = null;

    //Person Number	int
    @Id
    @Column(name ="Person Number")
    // @GeneratedValue
    private int personNumber;
    
    // Env_ID	bigint
    @Column(name="Env_ID")
    private int env_id;

    //TC_Name	text
    @Column(name="TC_Name")
    private String tcName;

    //Status	text
    @Column(name="Status")
    private String status;

    // Name	text
    @Column(name="Name")
    private String name;

    // Legal Employer Name	text
    @Column(name="Legal Employer Name")
    private String legalEmprName;

    // Business Unit Name	text
    @Column(name="Business Unit Name")
    private String buName;

    // Assignment Number	text
    @Column(name="Assignment Number")
    private String assgNumber;

    // Assignment Status	text
    @Column(name="Assignment Status")
    private String assgStatus;

    // Work At Home	text
    @Column(name="Work At Home")
    private String wfh;

    // First Name	text
    @Column(name="First Name")
    private String firstname;

    // Last Name	text
    @Column(name="Last Name")
    private String lastname;

    // Bargaining Unit	text
    @Column(name="Bargaining Unit")
    private String bargainUnit;

    // Full/Part Time	text
    @Column(name="Full/Part Time")
    private String fullPart;

    // Hourly/Salaried	text
    @Column(name="Hourly/Salaried")
    private String hrlySal;

    // Manager Name	text
    @Column(name="Manager Name")
    private String mgrName;

    // // Manager Person Number	int
    // @Column(name="Last Name")
    // private String lastname;

    // // Manager Flag	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Assignment Start Date	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Assignment End Date	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Department Name	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Job Code	int
    // @Column(name="Last Name")
    // private String lastname;

    // // Job Name	text
    // @Column(name="Last Name")
    // private String lastname;

    // // City	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Internal Location Code	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Location Name	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Labour Union Member Flag	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Assignment Category	text
    // @Column(name="Last Name")
    // private String lastname;

    // // Regular Scheduled Hours	int
    // @Column(name="Last Name")
    // private String lastname;

    // Hours before OT	int
    @Column(name="Hours before OT")
    private String hrsBfOT;

    // Shift	text
    @Column(name="Shift")
    private String shift;

    // Shift (Description)	text
    @Column(name="Shift (Description)")
    private String shiftDesc;

    // Worker Payroll Name	text
    @Column(name="Worker Payroll Name")
    private String workerpyName;

    // Collective Agreement Name	text
    @Column(name="Collective Agreement Name")
    private String colAgreementName;

    // Work Relationship Start Date	text
    @Column(name="Work Relationship Start Date")
    private String workRelStrDate;


}
