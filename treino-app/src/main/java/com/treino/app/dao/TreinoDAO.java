package com.treino.app.dao;

import com.treino.app.model.Treino;
import com.treino.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TreinoDAO {

    public void salvar(Treino treino){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(treino);

        tx.commit();
        session.close();
    }

    public List<Treino> listar(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Treino> treinos = session.createQuery("from Treino", Treino.class).list();

        session.close();
        return treinos;
    }
}
