package com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.model.AgendaTelefonicaEntity;

@Repository
public interface AgendaTelefonicaRepository extends JpaRepository<AgendaTelefonicaEntity, Long> {

}