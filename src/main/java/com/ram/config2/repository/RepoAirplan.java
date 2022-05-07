package com.ram.config2.repository;

import com.ram.config2.entity.Airplane;
import com.ram.config2.entity.Systeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoAirplan extends JpaRepository<Airplane, Long> {
    public Airplane findAirplaneByTailNumber(String tailNumber) ;

}
