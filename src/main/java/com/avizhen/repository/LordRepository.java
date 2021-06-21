package com.avizhen.repository;

import com.avizhen.entity.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LordRepository extends JpaRepository<Lord, Integer> {
    @Query("SELECT l FROM Lord l WHERE l.name = :name")
    Lord findByName(@Param("name") String name);
}
