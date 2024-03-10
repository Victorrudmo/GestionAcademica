package com.example.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion.modelos.Matricula;


public interface RepoMatricula extends JpaRepository<Matricula, Long>{
    
} 
