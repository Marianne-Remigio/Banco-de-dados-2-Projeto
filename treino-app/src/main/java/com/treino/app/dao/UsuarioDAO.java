package com.treino.app.dao;

import com.treino.app.model.Usuario;
import com.treino.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(usuario);

        tx.commit();
        session.close();
    }

    public List<Usuario> listar() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Usuario> usuarios = session
                .createQuery("from Usuario", Usuario.class)
                .list();

        session.close();
        return usuarios;
    }

    public Usuario buscarPorId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Usuario usuario = session.get(Usuario.class, id);

        session.close();
        return usuario;
    }

    public void atualizar(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(usuario);

        tx.commit();
        session.close();
    }

    public void deletar(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.remove(usuario);

        tx.commit();
        session.close();
    }
}
