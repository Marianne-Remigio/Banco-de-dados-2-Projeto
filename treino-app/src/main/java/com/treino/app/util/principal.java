package com.treino.app.util;

import com.treino.app.model.*;
import com.treino.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.util.Locale;

public class principal {

    public static void main(String[] args) {

        Transaction transaction = null;
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.CANADA);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            // coloca as informações ao vivo pro teste
            System.out.println("Digite o nome do usuario: ");
            String nome = sc.nextLine();

            System.out.println("Digite o e-mail do usuario: ");
            String email = sc.nextLine();

            Usuario usuario = new Usuario(nome, email);

            System.out.println("Digite o nome do treino: ");
            String nomeTreino = sc.nextLine();

            Treino treino = new Treino(nomeTreino, usuario);
            usuario.addTreino(treino);

            System.out.println("Digite o nome do exercicio: ");
            String nomeEx = sc.nextLine();

            Exercicio exercicio = new Exercicio(nomeEx);
            session.persist(exercicio); 

            ExecucaoExercicio exec = new ExecucaoExercicio();

            System.out.println("Digite o número de séries: ");
            if (!sc.hasNextInt()) throw new RuntimeException("Séries inválidas");
            exec.setSeries(sc.nextInt());

            System.out.println("Digite o número de repetições: ");
            if (!sc.hasNextInt()) throw new RuntimeException("Repetições inválidas");
            exec.setRepeticoes(sc.nextInt());

            System.out.println("Digite a carga: ");
            if (!sc.hasNextDouble()) throw new RuntimeException("Carga inválida");
            exec.setCarga(sc.nextDouble());

            exec.setExercicio(exercicio);
            treino.addExecucao(exec);

            // persistencia
            session.persist(usuario);

            transaction.commit();

            // consulta dos dados
            session.createQuery("from Treino", Treino.class)
                    .list()
                    .forEach(t -> {
                        System.out.println("\nTreino: " + t.getNome());
                        System.out.println("Usuario: " + t.getUsuario().getNome());

                        t.getExecucoes().forEach(e ->
                                System.out.println(
                                        "  -> " + e.getOrdem() +
                                        " | " + e.getExercicio().getNome() +
                                        " | " + e.getSeries() + "x" + e.getRepeticoes() +
                                        " | " + e.getCarga() + "kg"
                                )
                        );
                    });

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println("Erro na execução: " + e.getMessage());
        }

        sc.close();
    }
}
