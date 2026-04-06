package com.treino.app.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExecucaoExercicio> execucoes = new ArrayList<>();

    public Treino() {}

    public Treino(String nome, Usuario usuario){
        this.nome = nome;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ExecucaoExercicio> getExecucoes() {
        return Collections.unmodifiableList(execucoes);
    }

    public void addExecucao(ExecucaoExercicio exec) {
        execucoes.add(exec);
        exec.setTreino(this);
    }

    public void removeExecucao(ExecucaoExercicio exec) {
        execucoes.remove(exec);
        exec.setTreino(null);
    }
}
