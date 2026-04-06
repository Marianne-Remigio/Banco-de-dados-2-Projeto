package com.treino.app.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "exercicio")
    private List<ExecucaoExercicio> execucoes = new ArrayList<>();

    public Exercicio() {}

    public Exercicio(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ExecucaoExercicio> getExecucoes() {
        return Collections.unmodifiableList(execucoes);
    }
}
