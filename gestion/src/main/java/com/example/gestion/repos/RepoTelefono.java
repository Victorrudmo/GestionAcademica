package com.example.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion.modelos.Telefono;


public interface RepoTelefono extends JpaRepository<Telefono, Integer>{
    
}
