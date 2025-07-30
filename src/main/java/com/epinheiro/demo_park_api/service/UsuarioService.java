package com.epinheiro.demo_park_api.service;

import com.epinheiro.demo_park_api.entity.Usuario;
import com.epinheiro.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario); // save é fornecido pela JpaRepository. o UsuarioRepository herda dessa classe JpaRepository.
    }

    @Transactional(readOnly = true) // Especifica que é um mét0do apenas de consulta
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Usuário não encontrado com o ID: " + id)
                );

    }

    @Transactional //sem readOnly = true pois estamos fazendo uma escrita
    public Usuario editPassword(Long id, String password) {
        Usuario user = getUsuarioById(id);
        user.setPassword(password);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
}
