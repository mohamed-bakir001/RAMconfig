package com.ram.config2.controller;
import com.ram.config2.entity.Swlocation;
import com.ram.config2.exception.SwlocationNotFoundException;
import com.ram.config2.repository.RepoSwlocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ControllerSwlocation {
    RepoSwlocation repoSwlocation;

    @Autowired
    void ControllerSwloacation(RepoSwlocation repoSwlocation){
        this.repoSwlocation = repoSwlocation;
    }

    @GetMapping(value = "/Swlocation")
    public List<Swlocation> getAllSwloacation(){
        return repoSwlocation.findAll();
    }

    @GetMapping(value = "/Swlocation/{id}")
    public Swlocation getSwlocation(@PathVariable("id") Long id) throws SwlocationNotFoundException {
        return repoSwlocation.findById(id).orElseThrow(() -> new SwlocationNotFoundException(id)) ;
    }

    @PostMapping(value = "/Swlocation")
    Swlocation newSwlocation(@RequestBody Swlocation newSwlocation) {
        return repoSwlocation.save(newSwlocation);
    }


    @DeleteMapping(value = "/Swlocation/{id}")
    void deleteSwlocation(@PathVariable("id") Long id) {
        repoSwlocation.deleteById(id);
    }


    @PutMapping(value = "/Swlocation/{id}")
    Swlocation replaceSwlocation(@RequestBody Swlocation newSwlocation, @PathVariable("id") Long id) {

        return repoSwlocation.findById(id)
                .map(swlocation -> {
                    //fixed

                    return repoSwlocation.save(swlocation);
                })
                .orElseGet(() -> {
                    newSwlocation.setSwlocationId(id);
                    return repoSwlocation.save(newSwlocation);
                });
    }
}
