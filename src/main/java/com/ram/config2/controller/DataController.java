package com.ram.config2.controller;

import com.ram.config2.entity.*;
import com.ram.config2.exception.SwlocationNotFoundException;
import com.ram.config2.exception.SystemNotFoundException;
import com.ram.config2.repository.*;
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
    private DataManageService dataManageService;
    private UserRepository userRepository;



    @Autowired
    DataController(RepoLoadableSW repoLoadableSW ,
               RepoAirplan repoAirplan ,
               RepoSwlocation repoSwlocation ,
               RepoSystem repoSystem,
               DataManageService dataManageService,
                   UserRepository userRepository){
        this.repoAirplan = repoAirplan ;
        this.repoSwlocation = repoSwlocation ;
        this.repoSystem = repoSystem ;
        this.repoLoadableSW = repoLoadableSW ;
        this.dataManageService = dataManageService;
        this.userRepository = userRepository;

    }

    @GetMapping(value = "/airplan")
    public List<Airplane> getAllAirplan(){
        return this.repoAirplan.findAll();
    }

    @GetMapping(value = "/airplan/{id}")
    public Airplane getAirplan(@PathVariable Long id){
        return this.repoAirplan.findById(id).get();
    }


    @DeleteMapping(value = "/airplan/delete/{id}")
    public void deleteAirplan(@PathVariable long id ){
         this.dataManageService.deleteDataAirplan(id);
    }

    @GetMapping(value = "/system/{id}")
    public List<Systeme> getSystems(@PathVariable("id") Long id){
        return repoSystem.findSystemeByAirplane_AirpId(id);
    }

    @GetMapping(value = "/systeme/{id}")
    public Systeme getSystem(@PathVariable Long id){
        return this.repoSystem.findById(id).get();
    }


    @GetMapping(value = "/swlocation/{id}")
    public List<Swlocation> getSwlocation(@PathVariable("id") Long id)  {
        return repoSwlocation.findSwlocationBySysteme_SystemId(id) ;
    }

        @GetMapping(value = "/loadablesw/{id}")
    public List<LoadableSW> getLoadableSW(@PathVariable("id") Long id)  {
        return repoLoadableSW.findLoadableSWBySwlocation_SwlocationId(id) ;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers()  {
        return this.userRepository.findAll() ;
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable Long id)  {
        this.userRepository.deleteById(id); ;
    }
}
