/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.cyberjogos.administrador.repository;

import br.com.ifba.cyberjogos.administrador.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório responsável pelo acesso ao banco
 * de dados para a entidade Administrador.
 *
 * Herda automaticamente os métodos:
 *   save(), findAll(), findById(), deleteById()
 *
 * @author Italo
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    //Busca administradores pelo nível de acesso. Usado para filtrar por permissões no sistema.
    List<Administrador> findByNivelAcesso(Integer nivelAcesso);

    //Busca administradores pelo cargo.
    List<Administrador> findByCargoContainingIgnoreCase(String cargo);

}
