/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Paiement;
import bean.Reservation;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private ReservationFacade reservationFacade;

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

    public int save(Paiement selected) {
        if (!selected.getType().equals("ESP")) {
            return -1;
        }

        Reservation reservation = selected.getReservation();
        if( reservation == null){
            return -2;
        }
        if (reservation.getTotalCheq() == null) // to avoid  java.lang.NullPointerException
        {
            reservation.setTotalCheq(0.0);
        }
        if (reservation.getTotalCheq() == null) // to avoid  java.lang.NullPointerException
        {
            reservation.setTotalESP(0.0);
        }
        if (selected.getDateEncaissement() != null && selected.getType() == "check") {

            Double new_total = reservation.getTotalCheq() + selected.getMontant();
            reservation.setTotalCheq(new_total);   // TotalCheck

        } else {
            double new_total = reservation.getTotalESP() + selected.getMontant();
            reservation.setTotalESP(new_total);   // TotalESP
        }
        List<Paiement> new_list = reservation.getPaiements();
        new_list.add(selected);
        reservation.setPaiements(new_list);                                                 // set List Paiments
        Double total_payment = reservation.getTotalESP() + reservation.getTotalCheq();
        reservation.setTotalPaiement(total_payment); // set totalPayment
        create(selected);
        reservationFacade.edit(reservation);

        return 1;
    }

}
