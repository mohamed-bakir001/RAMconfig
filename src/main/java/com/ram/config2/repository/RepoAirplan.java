package com.ram.config2.repository;

import com.ram.config2.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAirplan extends JpaRepository<Airplane, Long> {

}
