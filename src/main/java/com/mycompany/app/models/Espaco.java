package com.mycompany.app.models;

import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private Integer capacidade;

    @ElementCollection
    private List<String> recursos;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Atividade> atividades;

    // Getters e Setters
}

