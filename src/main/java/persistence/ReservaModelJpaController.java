/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import model.ReservaModel;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author Camilo
 */
public class ReservaModelJpaController implements Serializable {

    public ReservaModelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ReservaModelJpaController() {
        emf = Persistence.createEntityManagerFactory("ControladorPersistencia");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReservaModel reservaModel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reservaModel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservaModel reservaModel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservaModel = em.merge(reservaModel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reservaModel.getIdrserva();
                if (findReservaModel(id) == null) {
                    throw new NonexistentEntityException("The reservaModel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservaModel reservaModel;
            try {
                reservaModel = em.getReference(ReservaModel.class, id);
                reservaModel.getIdrserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservaModel with id " + id + " no longer exists.", enfe);
            }
            em.remove(reservaModel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReservaModel> findReservaModelEntities() {
        return findReservaModelEntities(true, -1, -1);
    }

    public List<ReservaModel> findReservaModelEntities(int maxResults, int firstResult) {
        return findReservaModelEntities(false, maxResults, firstResult);
    }

    private List<ReservaModel> findReservaModelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReservaModel.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ReservaModel findReservaModel(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReservaModel.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaModelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReservaModel> rt = cq.from(ReservaModel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
