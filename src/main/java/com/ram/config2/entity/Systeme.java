package com.ram.config2.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
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

    @OneToMany(mappedBy = "systeme", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Swlocation> swlocations;


    @JoinColumn(name ="airpId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Airplane airplane;
}
