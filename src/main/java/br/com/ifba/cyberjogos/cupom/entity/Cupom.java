/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.cupom.entity;

import jakarta.persistence.*;

/**
 * Entidade que representa um Cupom de desconto
 * do sistema CyberJogos.
 *
 * @author Italo
 */
@Entity
@Table(name = "cupons")
public class Cupom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false, unique = true)
    private String codigo;

    
    @Column(nullable = false)
    private Double desconto;

    
    private String validade;

    
    @Column(name = "limite_uso")
    private Integer limiteUso;

    
    @Column(nullable = false)
    private Boolean ativo;

    public Cupom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Integer getLimiteUso() {
        return limiteUso;
    }

    public void setLimiteUso(Integer limiteUso) {
        this.limiteUso = limiteUso;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public String toString() { return codigo; }
}
