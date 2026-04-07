package com.treino.app.util;

import com.treino.app.model.ExecucaoExercicio;
import com.treino.app.model.Exercicio;
import com.treino.app.model.Treino;
import com.treino.app.model.Usuario;
import com.treino.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;
import java.util.Locale;

public class principal {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.CANADA);

        Usuario usuario = new Usuario();
        Treino treino = new Treino();
        Exercicio exercicio = new Exercicio();
        ExecucaoExercicio execucaoExercicio = new ExecucaoExercicio();

        System.out.println("Digite o nome do usuario: ");
        usuario.setNome(sc.nextLine());

        System.out.println("Digite o e-mail do usuario: ");
        usuario.setEmail(sc.nextLine());

        System.out.println("Digite o nome do treino: ");
        treino.setNome(sc.nextLine());

        System.out.println("Digite o nome do exercicio: ");
        exercicio.setNome(sc.nextLine());

        System.out.println("Digite o número de séries: ");
        execucaoExercicio.setSeries(sc.nextInt());

        System.out.println("Digite o número de repetições do exercício: ");
        execucaoExercicio.setRepeticoes(sc.nextInt());

        System.out.println("Digite o valor da carga do exercicio: ");
        execucaoExercicio.setCarga(sc.nextDouble());

        treino.setUsuario(usuario);

        execucaoExercicio.setTreino(treino);
        execucaoExercicio.setExercicio(exercicio);

        session.persist(usuario);
        session.persist(treino);
        session.persist(exercicio);
        session.persist(execucaoExercicio);

        transaction.commit();

        session.createQuery("from Treino", Treino.class)
                .list()
                .forEach(t -> {
                    System.out.println("\nTreino: " + t.getNome());
                    System.out.println("Usuario: " + t.getUsuario().getNome());
                });

        session.close();
        sc.close();
    }
}
