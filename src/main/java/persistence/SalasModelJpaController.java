/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import model.SalasModel;
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
public class SalasModelJpaController implements Serializable {

    public SalasModelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SalasModelJpaController() {
        emf = Persistence.createEntityManagerFactory("ControladorPersistencia");
    }

    public void create(SalasModel salasModel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(salasModel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalasModel salasModel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            salasModel = em.merge(salasModel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = salasModel.getIdespacio();
                if (findSalasModel(id) == null) {
                    throw new NonexistentEntityException("The salasModel with id " + id + " no longer exists.");
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
            SalasModel salasModel;
            try {
                salasModel = em.getReference(SalasModel.class, id);
                salasModel.getIdespacio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salasModel with id " + id + " no longer exists.", enfe);
            }
            em.remove(salasModel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SalasModel> findSalasModelEntities() {
        return findSalasModelEntities(true, -1, -1);
    }

    public List<SalasModel> findSalasModelEntities(int maxResults, int firstResult) {
        return findSalasModelEntities(false, maxResults, firstResult);
    }

    private List<SalasModel> findSalasModelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalasModel.class));
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

    public SalasModel findSalasModel(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalasModel.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalasModelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalasModel> rt = cq.from(SalasModel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
