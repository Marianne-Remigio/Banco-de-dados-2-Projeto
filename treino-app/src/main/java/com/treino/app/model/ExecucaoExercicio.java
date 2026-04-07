package com.treino.app.model;

import jakarta.persistence.*;

@Entity
public class ExecucaoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer series;
    private Integer repeticoes;
    private Double carga;
    private Integer ordem;

    @ManyToOne
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    public ExecucaoExercicio() {}

    public ExecucaoExercicio(Integer series, Integer repeticoes, Double carga, Integer ordem,
                             Treino treino, Exercicio exercicio){
        setSeries(series);
        setRepeticoes(repeticoes);
        setCarga(carga);
        setOrdem(ordem);
        setTreino(treino);
        setExercicio(exercicio);
    }

    public Long getId() {
        return id;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        if (series == null || series <= 0) {
            throw new IllegalArgumentException("Séries inválidas");
        }
        this.series = series;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        if (repeticoes == null || repeticoes <= 0) {
            throw new IllegalArgumentException("Repetições inválidas");
        }
        this.repeticoes = repeticoes;
    }

    public Double getCarga() {
        return carga;
    }

    public void setCarga(Double carga) {
        if (carga == null || carga < 0) {
            throw new IllegalArgumentException("Carga inválida");
        }
        this.carga = carga;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        if (ordem == null || ordem <= 0) {
            throw new IllegalArgumentException("Ordem inválida");
        }
        this.ordem = ordem;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        if (treino == null) {
            throw new IllegalArgumentException("Execução deve estar associada a um treino");
        }
        this.treino = treino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        if (exercicio == null) {
            throw new IllegalArgumentException("Execução deve ter um exercício");
        }
        this.exercicio = exercicio;
    }
}
