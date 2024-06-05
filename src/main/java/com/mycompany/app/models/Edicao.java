package com.mycompany.app.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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
    private String info_Incricao;

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

//VERIFICAR RELAÇÃO
    @ManyToMany
    @JoinTable(
        name = "edicao_organizadores",
        joinColumns = @JoinColumn(name = "edicao_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> organizadores;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @OneToMany(mappedBy = "edicao", cascade = CascadeType.ALL)
    private List<Atividade> atividades;

    @ManyToMany
    @JoinTable(
        name = "edicao_usuario",
        joinColumns = @JoinColumn(name = "edicao_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    // Getters e Setters
}
