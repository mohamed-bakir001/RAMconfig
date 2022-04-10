package com.ram.config2.repository;

import com.ram.config2.entity.Swlocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoSwlocation extends JpaRepository<Swlocation,Long> {
}
