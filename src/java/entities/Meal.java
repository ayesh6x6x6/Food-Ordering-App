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
import javax.persistence.ManyToOne;
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
@Table(name = "MEAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meal.findAll", query = "SELECT m FROM Meal m")
    , @NamedQuery(name = "Meal.findByMealId", query = "SELECT m FROM Meal m WHERE m.mealId = :mealId")
    , @NamedQuery(name = "Meal.findByMealName", query = "SELECT m FROM Meal m WHERE m.mealName = :mealName")
    , @NamedQuery(name = "Meal.findByMealDescr", query = "SELECT m FROM Meal m WHERE m.mealDescr = :mealDescr")
    , @NamedQuery(name = "Meal.findByPrice", query = "SELECT m FROM Meal m WHERE m.price = :price")})
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MEAL_ID")
    private Integer mealId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MEAL_NAME")
    private String mealName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MEAL_DESCR")
    private String mealDescr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;
  
    @ManyToMany(mappedBy = "mealList")
    private List<Orders> ordersList;
    @JoinColumn(name = "MEAL_PROMO", referencedColumnName = "PROMO_ID")
    @ManyToOne
    private Promotions mealPromo;
    @JoinColumn(name = "REST_ID", referencedColumnName = "REST_ID")
    @ManyToOne(optional = false)
    private Restaurant restId;

    public Meal() {
    }

    public Meal(Integer mealId) {
        this.mealId = mealId;
    }

    public Meal(Integer mealId, String mealName, String mealDescr, double price) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealDescr = mealDescr;
        this.price = price;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealDescr() {
        return mealDescr;
    }

    public void setMealDescr(String mealDescr) {
        this.mealDescr = mealDescr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Promotions getMealPromo() {
        return mealPromo;
    }

    public void setMealPromo(Promotions mealPromo) {
        this.mealPromo = mealPromo;
    }

    public Restaurant getRestId() {
        return restId;
    }

    public void setRestId(Restaurant restId) {
        this.restId = restId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mealId != null ? mealId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meal)) {
            return false;
        }
        Meal other = (Meal) object;
        if ((this.mealId == null && other.mealId != null) || (this.mealId != null && !this.mealId.equals(other.mealId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities2.Meal[ mealId=" + mealId + " ]";
    }
    
}
