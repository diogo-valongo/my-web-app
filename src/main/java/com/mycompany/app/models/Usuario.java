package com.mycompany.app.models;

import javax.persistence.*;

import com.mycompany.app.models.enums.TipoUsuario;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String afiliacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    @ManyToMany(mappedBy = "usuarios")
    private List<Edicao> edicoes;

    
    @ManyToMany(mappedBy = "organizadores")
    private List<Edicao> organizacoes;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Atividade> atividadesFavoritas;

    // Getters e Setters
}
