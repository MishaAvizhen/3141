package com.avizhen.repository;

import com.avizhen.entity.Universe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UniverseRepository extends JpaRepository<Universe, Integer> {
    @Query("SELECT u FROM  Universe u WHERE u.title = :title")
    Universe findByTitle(@Param("title") String title);
}