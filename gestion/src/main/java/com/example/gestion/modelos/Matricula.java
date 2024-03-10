package com.example.gestion.modelos;

import java.util.stream.Collectors;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    Usuario usuario; 
    @ManyToOne
    Asignaturas asignatura;

    @Transient
    public String getRolUsuario() {
        return this.usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.joining(", "));
    }
}
