package br.com.fiap.dao;

import br.com.fiap.entity.FuncionarioEntity;
import br.com.fiap.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * DAO para CRUD de FuncionarioEntity. Exibe o SQL em cada etapa (Create, Read, Update, Delete).
 */
public class FuncionarioDao {

    public FuncionarioEntity create(FuncionarioEntity f) {
        System.out.println("\n[CRUD - CREATE] SQL gerado (Reflection):");
        System.out.println(SqlGenerator.gerarInsert(f));
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            return f;
        } finally {
            em.close();
        }
    }

    public List<FuncionarioEntity> readAll() {
        System.out.println("\n[CRUD - READ] SQL gerado (Reflection):");
        System.out.println(SqlGenerator.gerarSelectTodos(FuncionarioEntity.class));
        System.out.println(SqlGenerator.gerarSelectComColunas(FuncionarioEntity.class));
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<FuncionarioEntity> q = em.createQuery("SELECT f FROM FuncionarioEntity f", FuncionarioEntity.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FuncionarioEntity readById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(FuncionarioEntity.class, id);
        } finally {
            em.close();
        }
    }

    public FuncionarioEntity update(FuncionarioEntity f) {
        System.out.println("\n[CRUD - UPDATE] SQL gerado (Reflection):");
        System.out.println(SqlGenerator.gerarUpdate(f));
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            FuncionarioEntity merged = em.merge(f);
            em.getTransaction().commit();
            return merged;
        } finally {
            em.close();
        }
    }

    public void delete(FuncionarioEntity f) {
        System.out.println("\n[CRUD - DELETE] SQL gerado (Reflection):");
        System.out.println(SqlGenerator.gerarDelete(FuncionarioEntity.class, f.getId()));
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(f) ? f : em.merge(f));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
