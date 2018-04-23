/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author PC
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReservation;
    private Double avance;
    private Double totalESP;
    private Double totalCheq;
    private Double totalPaiement;
    private int nbrDeTables;
    private Double montant;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "reservation")
    private List<Paiement> paiements;

    public Double getTotalESP() {
        return totalESP;
    }

    public void setTotalESP(Double totalESP) {
        this.totalESP = totalESP;
    }

    public Double getTotalCheq() {
        return totalCheq;
    }

    public void setTotalCheq(Double totalCheq) {
        this.totalCheq = totalCheq;
    }

    public Double getTotalPaiement() {
        return totalPaiement;
    }

    public void setTotalPaiement(Double totalPaiement) {
        this.totalPaiement = totalPaiement;
    }

    
    
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
  
    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Double getAvance() {
        return avance;
    }

    public void setAvance(Double avance) {
        this.avance = avance;
    }

    public int getNbrDeTables() {
        return nbrDeTables;
    }

    public void setNbrDeTables(int nbrDeTables) {
        this.nbrDeTables = nbrDeTables;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Fete[ id=" + id + " ]";
    }

}
