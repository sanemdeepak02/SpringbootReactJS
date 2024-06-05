package com.hcm.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.hcm.accessingdatamysql.entity.employee;
import java.util.List;


@Entity
@Table(name="environment")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Environment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int environmentid;

    @Column(name = "env_name", nullable = false, unique = true)
    private String envName;

    // @OneToMany(mappedBy = "environment")
    // private List<employee> employee;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "envID")
    private List<employee> employee;
}
