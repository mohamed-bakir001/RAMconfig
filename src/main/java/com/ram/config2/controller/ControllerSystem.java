package com.ram.config2.controller;

import com.ram.config2.entity.Systeme;
import com.ram.config2.exception.SystemNotFoundException;
import com.ram.config2.repository.RepoSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ControllerSystem {

    RepoSystem repoSystem;

    @Autowired
    void ControllerSystem(RepoSystem repoSystem){
        this.repoSystem = repoSystem;
    }

    @GetMapping(value = "/System")
    public List<Systeme> getAllSystem(){
        return repoSystem.findAll();
    }

    @GetMapping(value = "/System/{id}")
    public Systeme getSystem(@PathVariable("id") Long id){
        return repoSystem.findById(id).orElseThrow(() -> new SystemNotFoundException(id)) ;
    }

    @PostMapping(value = "/System")
    Systeme newSystem(@RequestBody Systeme newSysteme) {
        return repoSystem.save(newSysteme);
    }


    @DeleteMapping(value = "/System/{id}")
    void deleteSystem(@PathVariable("id") Long id) {
        repoSystem.deleteById(id);
    }


    @PutMapping(value = "/System/{id}")
    Systeme replaceSystem(@RequestBody Systeme newSysteme, @PathVariable("id") Long id) {

        return repoSystem.findById(id)
                .map(system -> {
                    // fixed
                    return repoSystem.save(system);
                })
                .orElseGet(() -> {
                    newSysteme.setSystemId(id);
                    return repoSystem.save(newSysteme);
                });
    }
}
