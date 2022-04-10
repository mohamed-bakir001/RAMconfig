package com.ram.config2.repository;

import com.ram.config2.entity.LoadableSW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoLoadableSW extends JpaRepository<LoadableSW, Long> {
}
