/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Reservation;
import controller.util.PdfUtil;
import controller.util.SearchUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author hp
 */
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

    public void generatePdf() throws JRException, IOException {
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("responsable", "Bader");
        PdfUtil.generatePdf(findAll(), params, "ListDesReservation" + System.currentTimeMillis(), "/jasper/reservationPDF.jasper");
    }

    
}
