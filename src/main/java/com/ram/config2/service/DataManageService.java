package com.ram.config2.service;

import com.ram.config2.entity.Airplane;
import com.ram.config2.entity.LoadableSW;
import com.ram.config2.entity.Swlocation;
import com.ram.config2.entity.Systeme;
import com.ram.config2.repository.RepoAirplan;
import com.ram.config2.repository.RepoLoadableSW;
import com.ram.config2.repository.RepoSwlocation;
import com.ram.config2.repository.RepoSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataManageService {

    private RepoAirplan repoAirplan ;
    private RepoSystem repoSystem;
    private RepoLoadableSW repoLoadableSW;
    private RepoSwlocation repoSwlocation ;

    @Autowired
    DataManageService(RepoSwlocation repoSwlocation,
                      RepoSystem repoSystem ,
                      RepoAirplan repoAirplan,
                      RepoLoadableSW repoLoadableSW ){
        this.repoAirplan = repoAirplan ;
        this.repoSwlocation = repoSwlocation ;
        this.repoLoadableSW = repoLoadableSW ;
        this.repoSystem = repoSystem ;
    }

    public void deleteDataAirplan (Long id){
        List<Systeme> systemes = repoSystem.findSystemeByAirplane_AirpId(id) ;
        for (Systeme system: systemes) {
            List<Swlocation> swls = repoSwlocation.findSwlocationBySysteme_SystemId(system.getSystemId()) ;
            for (Swlocation swl: swls) {
                List<LoadableSW> lsws = repoLoadableSW.findLoadableSWBySwlocation_SwlocationId(swl.getSwlocationId()) ;
                for (LoadableSW lsw : lsws) {
                    repoLoadableSW.delete(lsw);
                }
                repoSwlocation.delete(swl);
            }
            repoSystem.delete(system);
        }
        repoAirplan.deleteById(id) ;
    }

    public void deleteDataAirplan (String airplan){
        Airplane airplane = repoAirplan.findAirplaneByTailNumber(airplan);
        List<Systeme> systemes = repoSystem.findSystemeByAirplane_AirpId(airplane.getAirpId()) ;
        for (Systeme system: systemes) {
            List<Swlocation> swls = repoSwlocation.findSwlocationBySysteme_SystemId(system.getSystemId()) ;
            for (Swlocation swl: swls) {
                List<LoadableSW> lsws = repoLoadableSW.findLoadableSWBySwlocation_SwlocationId(swl.getSwlocationId()) ;
                for (LoadableSW lsw : lsws) {
                    repoLoadableSW.delete(lsw);
                }
                repoSwlocation.delete(swl);
            }
            repoSystem.delete(system);
        }
        repoAirplan.delete(airplane) ;
    }
}
