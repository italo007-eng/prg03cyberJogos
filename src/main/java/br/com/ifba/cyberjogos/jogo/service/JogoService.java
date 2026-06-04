/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.jogo.service;

import br.com.ifba.cyberjogos.jogo.model.Jogo;
import br.com.ifba.cyberjogos.jogo.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço responsável pelas regras de negócio
 * relacionadas à entidade Jogo.
 *
 * Esta classe fica entre o Controller/View e o Repository,
 * garantindo que a lógica de negócio fique centralizada.
 *
 * @author Italo
 */
@Service
public class JogoService {
    //injeção automatica do repositorio pelo string
    
    @Autowired
    private JogoRepository jogoRepository;
    
    /**
     * Salva um jogo novo ou atualiza um existente no banco.
     * Se o jogo tiver ID, faz UPDATE. Se não tiver, faz INSERT.
     *
     * @param jogo objeto a ser salvo
     * @return jogo salvo com ID gerado pelo banco
     */
    public Jogo salvar(Jogo jogo) {
        return jogoRepository.save(jogo);
    }
    
    /**
     * Retorna todos os jogos cadastrados no banco.
     *
     * @return lista com todos os jogos
     */
    public List<Jogo> listarTodos(){
        return jogoRepository.findAll();
    }
    
    /**
     * Busca um jogo específico pelo seu ID.
     *
     * @param id identificador do jogo
     * @return Optional contendo o jogo, ou vazio se não encontrado
     */
    public Optional<Jogo> buscarPorId(Long id) {
        return jogoRepository.findById(id);
    }
    
    /**
     * Busca jogos pelo título (pesquisa parcial).
     * Usado no campo de busca da tela de listagem.
     *
     * @param titulo texto a pesquisar
     * @return lista de jogos encontrados
     */
    public List<Jogo> buscarPorTitulo(String titulo) {
        return jogoRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    /**
     * Exclui um jogo do banco pelo seu ID.
     *
     * @param id identificador do jogo a ser excluído
     */
    public void excluir(Long id) {
        jogoRepository.deleteById(id);
    }
}
