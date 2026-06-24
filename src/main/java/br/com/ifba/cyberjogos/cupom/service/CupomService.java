/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.cupom.service;

import br.com.ifba.cyberjogos.cupom.entity.Cupom;
import br.com.ifba.cyberjogos.cupom.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço com regras de negócio
 * relacionadas à entidade Cupom.
 *
 * @author Italo
 */
@Service
public class CupomService {
     
    @Autowired
    private CupomRepository cupomRepository;

    
    public Cupom salvar(Cupom cupom) {
        return cupomRepository.save(cupom);
    }

    
    public List<Cupom> listarTodos() {
        return cupomRepository.findAll();
    }

    
    public Optional<Cupom> buscarPorId(Long id) {
        return cupomRepository.findById(id);
    }

    
    public Optional<Cupom> buscarPorCodigo(String codigo) {
        return cupomRepository.findByCodigo(codigo);
    }

    
    public boolean codigoJaCadastrado(String codigo) {
        return cupomRepository.findByCodigo(codigo).isPresent();
    }

    
    public List<Cupom> listarAtivos() {
        return cupomRepository.findByAtivo(true);
    }

    
    public void excluir(Long id) {
        cupomRepository.deleteById(id);
    }
}
