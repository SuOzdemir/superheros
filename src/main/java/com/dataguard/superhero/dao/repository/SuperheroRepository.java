package com.dataguard.superhero.dao.repository;

import com.dataguard.superhero.dao.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Integer> {

}
