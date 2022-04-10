package com.ram.config2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
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
@JsonPropertyOrder({"Type", "Value", "Description"})
public class Swlocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long swlocationId;

    @JacksonXmlProperty(isAttribute = true, localName = "Type")
    private String type ;

    @JacksonXmlProperty(isAttribute = true, localName = "Value")
    private String value ;

    @JacksonXmlProperty(isAttribute = true, localName = "Description")
    private String description ;

    @OneToMany(mappedBy = "swlocation")
    private List<LoadableSW> loadableSWs;

    @ManyToOne
    @JoinColumn(name = "systemId")
    private Systeme systeme;
}
