package com.example.gestion.modelos;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String nombre;
    private String apellido;
    private boolean enabled;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String username;
    @Column(length = 100)
    private String password;    
    @ManyToMany
    private List<Rol> roles;
    @OneToMany
    @JoinColumn(name = "usuario_id")
    List<Telefono> telefonos;
    @OneToMany
    @JoinColumn(name = "usuario_id")
    List<Matricula> matriculas; 
}
