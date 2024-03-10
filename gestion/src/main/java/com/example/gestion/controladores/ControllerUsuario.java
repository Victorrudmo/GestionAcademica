package com.example.gestion.controladores;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.gestion.modelos.Asignaturas;
import com.example.gestion.modelos.Matricula;
import com.example.gestion.modelos.Telefono;
import com.example.gestion.modelos.Usuario;
import com.example.gestion.repos.RepoAsignatura;
import com.example.gestion.repos.RepoMatricula;
import com.example.gestion.repos.RepoRol;
import com.example.gestion.repos.RepoTelefono;
import com.example.gestion.repos.RepoUsuario;


@Controller
@RequestMapping("/usuarios")
public class ControllerUsuario {
    
    @Autowired 
    private RepoUsuario repoUsuario;
    @Autowired
    private RepoTelefono repoTelefono;
    @Autowired
    private RepoRol repoRol;
    @Autowired
    private RepoAsignatura repoAsig;
    @Autowired
    private RepoMatricula repoMatricula;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/")    
    public String findAll(Model modelo) {
        List<Usuario> lUsuario = repoUsuario.findAll();
        modelo.addAttribute("usuarios", lUsuario);
        return "usuarios/usuarios";
    }

    @GetMapping("")
    public String findAll2(Model modelo) {
        return findAll(modelo);
    }
    
    
    @GetMapping("/add")
    public String addUsuario(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        modelo.addAttribute("telefonos", repoTelefono.findAll());
        modelo.addAttribute("roles", repoRol.findAll());
        modelo.addAttribute("asignaturas", repoAsig.findAll());
        return "usuarios/add";
    }

    @PostMapping("/add")
    public String addUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuario.setEnabled(true);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repoUsuario.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuarioForm(Model modelo, @PathVariable("id") Long id) {
        Optional<Usuario> oUsuario = repoUsuario.findById(id);
        if (oUsuario.isPresent())
            modelo.addAttribute(
            "usuario", oUsuario.get());
        else {
            modelo.addAttribute(
                "mensaje", "El usuario consultado no existe.");
            return "error";
        }
        return "usuarios/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable("id") Long id) {
        try {
            Optional<Usuario> usuarioOptional = repoUsuario.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                List<Matricula> matriculas = usuario.getMatriculas();
                for (Matricula matricula : matriculas) {
                    repoMatricula.deleteById(matricula.getId());
                }
                repoUsuario.deleteById(id);
            } else {
                return "error"; 
            }

        } catch (Exception e) {
            return "error";
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/edit/{id}")
    public String editUsuarioForm(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional <Usuario> oUsuario = repoUsuario.findById(id);
        if(oUsuario.isPresent()) {
            modelo.addAttribute(
            "usuario", oUsuario.get());
            modelo.addAttribute(
                "roles", repoRol.findAll());
            return "usuarios/edit";
        } else {
            modelo.addAttribute("mensaje", "El usuario consultado no existe.");
            return "error";
        }
    }

    @GetMapping("/{id}/telefonos/add")
    public String telefonoAddForm(
        @PathVariable Long id,
        Model modelo) {
        
        Optional<Usuario> oUsuario = repoUsuario.findById(id);

        if (!oUsuario.isPresent()) {
            modelo.addAttribute(
                "mensaje", "El usuario no existe");
            return "error";
        }

        modelo.addAttribute(
            "telefono", new Telefono());
        
        return "usuarios/telefonos/add";
    }
 
    @PostMapping("/{id}/telefonos/add")
    public String telefonoAdd(
        @PathVariable @NonNull Long id,
        @ModelAttribute("telefono") Telefono telefono, 
        Model modelo) {
        
        Optional<Usuario> oUsuario = repoUsuario.findById(id);

        if (!oUsuario.isPresent()) {
            modelo.addAttribute(
                "mensaje", "El usuario no existe");
            return "error";
        }

        telefono.setUsuario(oUsuario.get());
        repoTelefono.save(telefono);        
        
        
        return "redirect:/usuarios";
    }
    
    @GetMapping("/{id}/telefonos")
    public String telefonos(
        @PathVariable Long id,
        Model modelo) {
        
        Optional<Usuario> oUsuario = repoUsuario.findById(id);

        if (!oUsuario.isPresent()) {
            modelo.addAttribute(
                "mensaje", "El usuario no existe");
            return "error";
        }

        modelo.addAttribute(
            "usuarios", repoUsuario.findAll());
        modelo.addAttribute(
             "usuarioActual", oUsuario.get());
        modelo.addAttribute(
             "telefonos", oUsuario.get().getTelefonos());

        modelo.addAttribute(
            "telefono", new Telefono());
        
        return "usuarios/telefonos/telefonos";
    }

   @GetMapping("/{id}/matriculas/add")
    public String matriculaAddForm(
        @PathVariable Long id,
        Model modelo) {
        
        Optional<Usuario> oUsuario = repoUsuario.findById(id);

        if (!oUsuario.isPresent()) {
            modelo.addAttribute(
                "mensaje", "El usuario no existe");
            return "error";
        }

        Optional<Asignaturas> oAsignatura = repoAsig.findById(id);

        if (!oAsignatura.isPresent()) {
            modelo.addAttribute(
                "mensaje", "No hay mas asignaturas");
            return "error";
        }
        modelo.addAttribute(
             "asignatura_actual", oAsignatura.get());
        modelo.addAttribute(
            "asignaturas", repoAsig.findAll());
        modelo.addAttribute(
            "matricula", new Matricula());
        
        return "usuarios/matriculas/add";
    }
 
    @PostMapping("/{id}/matriculas/add")
public String matriculaAdd(
    @PathVariable @NonNull Long id,
    @ModelAttribute("matricula") Matricula matricula, 
    @RequestParam("asignaturaId") Long asignaturaId,  
    Model modelo) {

    Optional<Usuario> oUsuario = repoUsuario.findById(id);
    Optional<Asignaturas> oAsignatura = repoAsig.findById(asignaturaId);

    if (!oUsuario.isPresent() || !oAsignatura.isPresent()) {
        modelo.addAttribute("mensaje", "El usuario o la asignatura no existe");
        return "error";
    }

    matricula.setUsuario(oUsuario.get());
    matricula.setAsignatura(oAsignatura.get());
    repoMatricula.save(matricula);

    return "redirect:/usuarios";
}

    @GetMapping("/{id}/matriculas")
    public String matriculas(
        @PathVariable Long id,
        Model modelo) {
        
        Optional<Usuario> oUsuario = repoUsuario.findById(id);

        if (!oUsuario.isPresent()) {
            modelo.addAttribute(
                "mensaje", "El usuario no existe");
            return "error";
        }

        modelo.addAttribute(
            "usuarios", repoUsuario.findAll());
        modelo.addAttribute(
             "usuarioActual", oUsuario.get());
        modelo.addAttribute(
             "matriculas", oUsuario.get().getMatriculas());

        modelo.addAttribute(
            "matricula", new Matricula());
        
        return "usuarios/matriculas/matriculas";
    }

    @GetMapping("/matriculas/delete/{id}")
    public String deleteMatricula(
            @PathVariable("id") @NonNull Long id) {
        try {
            repoMatricula.deleteById(id);
        } catch (Exception e) {
            return "error";
        }
        return "redirect:/usuarios";
    }
}
