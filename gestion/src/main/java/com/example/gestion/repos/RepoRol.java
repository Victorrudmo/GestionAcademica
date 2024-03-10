package com.example.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion.modelos.Rol;


public interface RepoRol extends JpaRepository<Rol, Long>{
    
}
