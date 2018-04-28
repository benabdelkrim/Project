/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Reservation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }
    
    public int finByDateReservation( Reservation reservation ){
        List<Reservation> reservations = findAll();
        for (Reservation reservation1 : reservations) {
            if(reservation1.getDateReservation().equals(reservation.getDateReservation()) ){
                return -1;
            }
        }
        
        return 1;
    }
    
    public int save(Reservation reservation){
        int reservation_db = finByDateReservation(reservation);
        
        if( reservation_db == -1 ){
            return -1;
        }
        int nb_tables_despo = reservation.getSalle().getNbrTable();
        int nb_table_newReservation = reservation.getNbrDeTables();
        if(nb_table_newReservation > nb_tables_despo){
            return -2;
        }
        create(reservation);
        return 1;
    }
    
}
