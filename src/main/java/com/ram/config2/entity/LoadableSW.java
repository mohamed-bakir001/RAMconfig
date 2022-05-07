package com.ram.config2.entity;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoadableSW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String description ;
    private String partNumber ;

    @ManyToOne()
    @JoinColumn(name = "swlocationId")
    private Swlocation swlocation;
}
