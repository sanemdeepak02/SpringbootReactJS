package com.hcm.accessingdatamysql.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="environment")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Environment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "env_id", nullable = false, unique = true)
    private int envID;
    @Column(name = "env_name", nullable = false, unique = true)
    private String envName;
}
