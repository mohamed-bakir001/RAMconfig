package com.ram.config2.controller;


import com.ram.config2.entity.Airplane;
import com.ram.config2.exception.AirplanNotFoundException;
import com.ram.config2.repository.RepoAirplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerAirplan {
    RepoAirplan repoAirplan;

    @Autowired
    ControllerAirplan(RepoAirplan repoAirplan){
        this.repoAirplan = repoAirplan;
    }

    @GetMapping(value = "/Airplan")
    public List<Airplane> getAllAirplan(){
        return repoAirplan.findAll();
    }

    @GetMapping(value = "/Airplan/{id}")
    public Airplane getAirplan(@PathVariable Long id){
        return repoAirplan.findById(id).orElseThrow(() -> new AirplanNotFoundException(id)) ;
    }

    @PostMapping(value = "/Airplan")
    Airplane newAirplan(@RequestBody Airplane newAirplane) {
        return repoAirplan.save(newAirplane);
    }


    @DeleteMapping(value = "/Airplan/{id}")
    void deleteAirplan(@PathVariable Long id) {
        repoAirplan.deleteById(id);
    }


    @PutMapping(value = "/Airplan/{id}")
    Airplane replaceAirplan(@RequestBody Airplane newAirplane, @PathVariable Long id) {

        return repoAirplan.findById(id)
                .map(airplane -> {
                    //check....!
                    airplane.setTailNumber(newAirplane.getTailNumber());
                    return repoAirplan.save(airplane);
                })
                .orElseGet(() -> {
                    newAirplane.setAirpId(id);
                    return repoAirplan.save(newAirplane);
                });
    }
}
