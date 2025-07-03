package com.cleosilva.library.controller;

import com.cleosilva.library.model.Usuario;
import com.cleosilva.library.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listUsers(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscaPorId(id));
    }
}
