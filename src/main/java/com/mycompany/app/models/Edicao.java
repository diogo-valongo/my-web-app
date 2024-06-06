package com.mycompany.app.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Edicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String cidade;
    
    @Column(nullable = false)
    private String info_Inscricao;

    @Column(nullable = false)
    private String precosPorLote;

    @Column(nullable = false)
    private String link_Inscricao;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicial;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSubmissao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDivulgacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntregaFinal;

    @Column(columnDefinition = "TEXT")
    private String chamadaTrabalhos;

    @ManyToMany
    @JoinTable(
    name = "edicao_organizadores",
    joinColumns = @JoinColumn(name = "edicao_id"),
    inverseJoinColumns = @JoinColumn(name = "usuario_id")
)
private List<Usuario> organizadores = new ArrayList<>();

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "evento_id", nullable = false)
private Evento evento;

@OneToMany(mappedBy = "edicao", cascade = CascadeType.ALL)
private List<Atividade> atividades = new ArrayList<>();

@ManyToMany
@JoinTable(
    name = "edicao_usuario",
    joinColumns = @JoinColumn(name = "edicao_id"),
    inverseJoinColumns = @JoinColumn(name = "usuario_id")
)
private List<Usuario> usuarios = new ArrayList<>();

    // Getters e Setters
}
