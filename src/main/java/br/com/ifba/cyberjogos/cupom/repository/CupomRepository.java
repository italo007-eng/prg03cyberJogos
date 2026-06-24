/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.cyberjogos.cupom.repository;

import br.com.ifba.cyberjogos.cupom.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso ao banco
 * de dados para a entidade Cupom.
 *
 * Herda automaticamente os métodos:
 *   save(), findAll(), findById(), deleteById()
 *
 * @author Italo
 */
@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
     
    Optional<Cupom> findByCodigo(String codigo);

    
    List<Cupom> findByAtivo(Boolean ativo);
}
