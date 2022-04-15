package com.ram.config2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airpId;
    private Date date;

    private String TailNumber;

    @OneToMany( mappedBy = "airplane",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Systeme> systemes;
}
