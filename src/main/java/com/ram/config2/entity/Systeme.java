package com.ram.config2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Systeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemId;
    private String name ;

    @OneToMany(mappedBy = "systeme")
    private List<Swlocation> swlocations;

    @ManyToOne
    @JoinColumn(name ="airpId")
    private Airplane airplane;
}
