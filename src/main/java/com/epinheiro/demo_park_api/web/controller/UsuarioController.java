package com.epinheiro.demo_park_api.web.controller;

import com.epinheiro.demo_park_api.entity.Usuario;
import com.epinheiro.demo_park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario ){
        Usuario user =  usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}") // Isso aqui é parâmetro de caminho Path Parameter (exemplo de como vai no postman:  http://localhost:8080/api/v1/usuarios/6)
    public ResponseEntity<Usuario> getById (@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Respondendo
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updatePassword(@PathVariable Long id ,@RequestBody Usuario usuario ) {
        Usuario user = usuarioService.editPassword(id, usuario.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll () {
        List<Usuario> users = usuarioService.getAll();
        return ResponseEntity.ok(users);
    }

}
