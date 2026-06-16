/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.administrador.service;

import br.com.ifba.cyberjogos.administrador.entity.Administrador;
import br.com.ifba.cyberjogos.administrador.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço com regras de negócio
 * relacionadas à entidade Administrador.
 *
 * @author Italo
 */
@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    public Administrador salvar(Administrador administrador){
        return administradorRepository.save(administrador);
    }
    
    public List<Administrador> listarTodos(){
        return administradorRepository.findAll();
    }
    
    public Optional<Administrador> buscarPorId(Long id) {
        return administradorRepository.findById(id);
    }
    
    public List<Administrador> buscarPorNivel(Integer nivelAcesso) {
        return administradorRepository.findByNivelAcesso(nivelAcesso);
    }
    
    public void excluir(Long id){
        administradorRepository.deleteById(id);
    }
}
