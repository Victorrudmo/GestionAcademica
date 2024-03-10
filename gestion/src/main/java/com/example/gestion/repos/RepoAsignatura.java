package com.example.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion.modelos.Asignaturas;


public interface RepoAsignatura extends JpaRepository<Asignaturas, Long>{
    
}
