package com.ram.config2.repository;

import com.ram.config2.entity.Swlocation;
import com.ram.config2.entity.Systeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoSwlocation extends JpaRepository<Swlocation,Long> {

    public List<Swlocation> findSwlocationBySysteme_SystemId(Long id);

}
