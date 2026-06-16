/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.administrador.entity;

import br.com.ifba.cyberjogos.usuario.entity.Usuario;
import jakarta.persistence.*;

/**
 * Entidade que representa um Administrador do sistema CyberJogos.
 * Herda os atributos básicos de Usuario (nome, email, senha).
 *
 * No banco cria a tabela "administradores" com apenas
 * os atributos específicos, ligada à tabela "usuarios"
 * pelo ID (estratégia JOINED).
 *
 * @author Italo
 */
@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id")
public class Administrador extends Usuario {
    
    /**
     * Nível de acesso do administrador.
     * 1 = Básico, 2 = Gerente, 3 = Super Admin
     */
    @Column(name = "nivel_acesso", nullable = false)
    private Integer nivelAcesso;
    
    private String cargo;

    public Administrador() {
    }

    public Integer getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
    
}
