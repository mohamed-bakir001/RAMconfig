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

public class Swlocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swlocationId;
    private String type ;
    private String value ;

    private String description ;

    @OneToMany(mappedBy = "swlocation", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LoadableSW> loadableSWs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "systemId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Systeme systeme;
}
