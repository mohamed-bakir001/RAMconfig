package com.ram.config2.repository;

import com.ram.config2.entity.Systeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoSystem extends JpaRepository<Systeme, Long>{

}


