package com.mycompany.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String sigla;

    @Column(nullable = false)
    private String caminho;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Edicao> edicoes;
    
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getCaminho() {
        return caminho;
    }

    public List<Edicao> getEdicoes() {
        return edicoes;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public void setEdicoes(List<Edicao> edicoes) {
        this.edicoes = edicoes;
    }
}
