/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Paiement;
import bean.Reservation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class PaiementFacade extends AbstractFacade<Paiement> {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaiementFacade() {
        super(Paiement.class);
    }
    public List<Paiement> findByReservation(Reservation reservation) {
        return em.createQuery("select P from Paiement P where P.reservation.id =" + reservation.getId()).getResultList();
    }
    
}
