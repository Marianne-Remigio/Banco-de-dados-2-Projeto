package com.treino.app.model;

import jakarta.persistence.*;

@Entity
public class ExecucaoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int series;
    private int repeticoes;
    private double carga;

    @ManyToOne
    private Treino treino;

    @ManyToOne
    private Exercicio exercicio;

    public ExecucaoExercicio() {}

    public ExecucaoExercicio(int series, int repeticoes, double carga, Treino treino, Exercicio exercicio){
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.treino = treino;
        this.exercicio = exercicio;
    }

    public Long getId() {
        return id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }
}
