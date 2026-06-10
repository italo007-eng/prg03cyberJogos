/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.cyberjogos.usuario.repository;
import br.com.ifba.cyberjogos.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositório base para operações de banco
 * relacionadas à entidade Usuario.
 *
 * @author Italo
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca um usuário pelo e-mail.
     * Usado para verificar duplicidade no cadastro.
     *
     * @param email e-mail a buscar
     * @return usuario encontrado ou vazio
     */
    Optional<Usuario> findByEmail(String email);
}
