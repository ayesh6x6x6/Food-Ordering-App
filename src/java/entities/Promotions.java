/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ayesh
 */
@Entity
@Table(name = "PROMOTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotions.findAll", query = "SELECT p FROM Promotions p")
    , @NamedQuery(name = "Promotions.findByPromoId", query = "SELECT p FROM Promotions p WHERE p.promoId = :promoId")
    , @NamedQuery(name = "Promotions.findByPromoName", query = "SELECT p FROM Promotions p WHERE p.promoName = :promoName")
    , @NamedQuery(name = "Promotions.findByPromoDescr", query = "SELECT p FROM Promotions p WHERE p.promoDescr = :promoDescr")
    , @NamedQuery(name = "Promotions.findByPriceDiscount", query = "SELECT p FROM Promotions p WHERE p.priceDiscount = :priceDiscount")})
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROMO_ID")
    private Integer promoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROMO_NAME")
    private String promoName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "PROMO_DESCR")
    private String promoDescr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE_DISCOUNT")
    private Double priceDiscount;
    @JoinTable(name = "RESTAURANT_PROMOS", joinColumns = {
        @JoinColumn(name = "PROMO_ID", referencedColumnName = "PROMO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "REST_ID", referencedColumnName = "REST_ID")})
    @ManyToMany
    private List<Restaurant> restaurantList;

    public Promotions() {
    }

    public Promotions(Integer promoId) {
        this.promoId = promoId;
    }

    public Promotions(Integer promoId, String promoName, String promoDescr) {
        this.promoId = promoId;
        this.promoName = promoName;
        this.promoDescr = promoDescr;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getPromoDescr() {
        return promoDescr;
    }

    public void setPromoDescr(String promoDescr) {
        this.promoDescr = promoDescr;
    }

    public Double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    @XmlTransient
    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promoId != null ? promoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotions)) {
            return false;
        }
        Promotions other = (Promotions) object;
        if ((this.promoId == null && other.promoId != null) || (this.promoId != null && !this.promoId.equals(other.promoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Promotions[ promoId=" + promoId + " ]";
    }
    
}
