package com.example.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion.modelos.Usuario;


public interface RepoUsuario extends JpaRepository<Usuario, Long>{

}
