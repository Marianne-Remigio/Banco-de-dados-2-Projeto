package com.treino.app;

import com.treino.app.model.*;
import com.treino.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // 1. Criar exercícios (catálogo)
        Exercicio supino = new Exercicio("Supino");
        Exercicio agachamento = new Exercicio("Agachamento");

        // persistir catalogo
        session.persist(supino);
        session.persist(agachamento);

        // cria usuario
        Usuario u = new Usuario("Kaio", "kaio@email.com");

        // cria treino e associação
        Treino t = new Treino("Treino A", u);
        u.addTreino(t);

        // execuções
        ExecucaoExercicio e1 = new ExecucaoExercicio();
        e1.setExercicio(supino);
        e1.setSeries(3);
        e1.setRepeticoes(10);
        e1.setCarga(50.0);
        e1.setOrdem(1);

        ExecucaoExercicio e2 = new ExecucaoExercicio();
        e2.setExercicio(agachamento);
        e2.setSeries(4);
        e2.setRepeticoes(8);
        e2.setCarga(80.0);
        e2.setOrdem(2);

        // associação correta
        t.addExecucao(e1);
        t.addExecucao(e2);

        // 5. Persistência do agregado
        session.persist(u);
        tx.commit();
        session.close();

        // VALIDAÇÃO
        Session session2 = HibernateUtil.getSessionFactory().openSession();

        session2.createQuery("from Treino", Treino.class)
                .list()
                .forEach(treino -> {
                    System.out.println("Treino: " + treino.getNome());
                    System.out.println("Usuario: " + treino.getUsuario().getNome());

                    treino.getExecucoes().forEach(exec ->
                            System.out.println(
                                    "  -> " + exec.getOrdem() +
                                            " | " + exec.getExercicio().getNome() +
                                            " | " + exec.getSeries() + "x" + exec.getRepeticoes() +
                                            " | " + exec.getCarga() + "kg"
                            )
                    );
                });

        session2.close();

        System.out.println("\nFluxo correto executado com sucesso.");
    }
}
