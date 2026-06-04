/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.cyberjogos.jogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade que representa um jogo no sistema Cyberjogos
 * Mapeada para a tabela "jogos" no banco de dados supabase
 * @author italo
 */
@Entity
@Table(name = "jogos")
public class Jogo {
    //identificador unico do jogo gerado automaticamente pelo banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // titulo do jogo
    @Column(nullable = false)
    private String titulo;
    
    //descricao detalhada do jogo
    private String descricao;
    
    //nome da empresa que desenvolve o jogo
    private String desenvolvedor;
    
    //preco de venda do jogo
   @Column(nullable = false)
   private double preco;
   
   //quantidade disponivel em estoque
   @Column(nullable = false)
   private Integer quantidadeEstoque;
   
   //plataforma do jogo: pc, ps5, xbox, switch
   @Column(nullable = false)
   private String plataforma;
   
   //genero do jogo: rpg, acao,fps,etc
   @Column(nullable = false)
   private String genero;
   
   //classificacao etaria: livre,10+,16+,18+
   private String classificacaoEtaria;

    public Jogo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }
   
    /**
     * Retorna o título do jogo como representação textual.
     * Usado pela JTable para exibir o objeto corretamente.
     */
    @Override
    public String toString() { return titulo; }
   
   
}
