package com.ram.config2.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Swlocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swlocationId;
    private String type ;
    private String value ;

    private String description ;

    @OneToMany(mappedBy = "swlocation")
    @JsonIgnore
    private List<LoadableSW> loadableSWs;

    @ManyToOne()
    @JoinColumn(name = "systemId")
    private Systeme systeme;
}
