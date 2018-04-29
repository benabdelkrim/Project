/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Salle;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class SalleFacade extends AbstractFacade<Salle> {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalleFacade() {
        super(Salle.class);
    }
    
    public List<Salle> verifierDisponnibiliteSalle(Date dateReservation) {
        String query = "select R.salle from Reservation R where 1=1 ";
        if (dateReservation != null) {
            query += SearchUtil.addConstraintDate("R", "dateReservation", "=", dateReservation);
        }
        return em.createQuery(query).getResultList();
    }   
    
}
