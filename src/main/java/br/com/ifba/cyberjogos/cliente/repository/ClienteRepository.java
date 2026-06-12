/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.cyberjogos.cliente.repository;
import br.com.ifba.cyberjogos.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo acesso ao banco
 * de dados para a entidade Cliente.
 *
 * Herda automaticamente os métodos:
 *   save(), findAll(), findById(), deleteById()
 *
 * @author Italo
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Busca cliente pelo CPF.
     * Usado para verificar duplicidade no cadastro.
     *
     * @param cpf CPF a buscar
     * @return cliente encontrado ou vazio
     */
    Optional<Cliente> findByCpf(String cpf);

    /**
     * Busca clientes pelo nome (parcial).
     * Usado no campo de pesquisa da tela de listagem.
     *
     * @param nome texto a pesquisar
     * @return lista de clientes encontrados
     */
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
