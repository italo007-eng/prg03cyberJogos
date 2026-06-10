/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.usuario.service;
import br.com.ifba.cyberjogos.usuario.entity.Usuario;
import br.com.ifba.cyberjogos.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço com regras de negócio
 * relacionadas à entidade Usuario.
 *
 * @author Italo
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Salva ou atualiza um usuário no banco.
     *
     * @param usuario objeto a salvar
     * @return usuario salvo com ID gerado
     */
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public boolean emailJaCadastrado(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
    
    public void excluir(Long id){
        usuarioRepository.deleteById(id);
    }
}
