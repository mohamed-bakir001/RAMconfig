package com.ram.config2.controller;

import com.ram.config2.entity.Airplane;
import com.ram.config2.entity.LoadableSW;
import com.ram.config2.entity.Swlocation;
import com.ram.config2.entity.Systeme;
import com.ram.config2.exception.SwlocationNotFoundException;
import com.ram.config2.exception.SystemNotFoundException;
import com.ram.config2.repository.RepoAirplan;
import com.ram.config2.repository.RepoLoadableSW;
import com.ram.config2.repository.RepoSwlocation;
import com.ram.config2.repository.RepoSystem;
import com.ram.config2.service.DataManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class DataController {

    private RepoAirplan repoAirplan;
    private RepoSystem repoSystem ;
    private RepoSwlocation repoSwlocation ;
    private RepoLoadableSW repoLoadableSW ;



    @Autowired
    DataController(RepoLoadableSW repoLoadableSW ,
               RepoAirplan repoAirplan ,
               RepoSwlocation repoSwlocation ,
               RepoSystem repoSystem,
               DataManageService dataManageService){
        this.repoAirplan = repoAirplan ;
        this.repoSwlocation = repoSwlocation ;
        this.repoSystem = repoSystem ;
        this.repoLoadableSW = repoLoadableSW ;

    }

    @GetMapping(value = "/airplan")
    public List<Airplane> getAllAirplan(){
        return repoAirplan.findAll();
    }

    @GetMapping(value = "/system/{id}")
    public List<Systeme> getSystem(@PathVariable("id") Long id){
        return repoSystem.findSystemeByAirplane_AirpId(id);
    }

    @GetMapping(value = "/swlocation/{id}")
    public List<Swlocation> getSwlocation(@PathVariable("id") Long id)  {
        return repoSwlocation.findSwlocationBySysteme_SystemId(id) ;
    }

        @GetMapping(value = "/loadablesw/{id}")
    public List<LoadableSW> getLoadableSW(@PathVariable("id") Long id)  {
        return repoLoadableSW.findLoadableSWBySwlocation_SwlocationId(id) ;
    }
}
