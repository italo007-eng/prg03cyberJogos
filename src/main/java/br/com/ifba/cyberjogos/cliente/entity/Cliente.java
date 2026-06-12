/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.cliente.entity;
import br.com.ifba.cyberjogos.usuario.entity.Usuario;
import jakarta.persistence.*;
/**
 * Entidade que representa um Cliente do sistema CyberJogos.
 * Herda os atributos básicos de Usuario (nome, email, senha).
 *
 * No banco de dados, cria a tabela "clientes" com apenas
 * os atributos específicos do cliente, ligada à tabela
 * "usuarios" pelo ID (estratégia JOINED).
 *
 * @author Italo
 */
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuario {
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    private String endereco;
    
    private String telefone;

    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
    
}
