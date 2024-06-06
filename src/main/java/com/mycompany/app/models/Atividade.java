package com.mycompany.app.models;

import javax.persistence.*;

import org.springframework.lang.Nullable;

import com.mycompany.app.models.enums.TipoAtividade;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAtividade tipoEnum;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date horarioInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date horarioFinal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "edicao_id", nullable = false)
    private Edicao edicao;

    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_id")
    private Espaco local;

    @ManyToMany
    @JoinTable(
        name = "usuario_atividade",
        joinColumns = @JoinColumn(name = "atividade_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios = new ArrayList<>();

    

   
}
