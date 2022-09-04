package com.dataguard.superhero.dao.repository;

import com.dataguard.superhero.dao.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {
}
