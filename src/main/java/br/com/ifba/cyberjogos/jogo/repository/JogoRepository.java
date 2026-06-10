/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.cyberjogos.jogo.repository;
import br.com.ifba.cyberjogos.jogo.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório responsável pelo acesso ao banco de dados para a entidade Jogo.
 *
 * Ao estender JpaRepository, os seguintes métodos já ficam disponíveis
 * automaticamente pelo Spring Data JPA, sem precisar escrever SQL:
 *   - save(jogo)          → INSERT ou UPDATE
 *   - findAll()           → SELECT todos
 *   - findById(id)        → SELECT por ID
 *   - deleteById(id)      → DELETE por ID
 *
 * @author Italo
 */
@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long>  {
   /**
     * Busca jogos cujo título contenha o texto informado.
     * Ignorando maiúsculas e minúsculas.
     * Usado no campo de pesquisa da tela de listagem.
     *
     * Exemplo: buscarPorTitulo("elden") → retorna "Elden Ring"
     *
     * @param titulo texto a ser pesquisado
     * @return lista de jogos encontrados
     */
    List<Jogo> findByTituloContainingIgnoreCase(String titulo);
    
    /**
     * Busca todos os jogos de uma plataforma específica.
     * Usado no filtro da tela de listagem.
     *
     * @param plataforma nome da plataforma (PC, PS5, Xbox, Switch)
     * @return lista de jogos da plataforma
     */
    List<Jogo> findByPlataforma(String plataforma);
}
