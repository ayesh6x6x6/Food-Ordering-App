/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "RESTAURANT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r")
    , @NamedQuery(name = "Restaurant.findByRestId", query = "SELECT r FROM Restaurant r WHERE r.restId = :restId")
    , @NamedQuery(name = "Restaurant.findByRestName", query = "SELECT r FROM Restaurant r WHERE r.restName = :restName")
    , @NamedQuery(name = "Restaurant.findByLocation", query = "SELECT r FROM Restaurant r WHERE r.location = :location")
    , @NamedQuery(name = "Restaurant.findByLatittude", query = "SELECT r FROM Restaurant r WHERE r.latittude = :latittude")
    , @NamedQuery(name = "Restaurant.findByLongitude", query = "SELECT r FROM Restaurant r WHERE r.longitude = :longitude")
    , @NamedQuery(name = "Restaurant.findByCuisine", query = "SELECT r FROM Restaurant r WHERE r.cuisine = :cuisine")
    , @NamedQuery(name = "Restaurant.findByRevenue", query = "SELECT r FROM Restaurant r WHERE r.revenue = :revenue")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REST_ID")
    private Integer restId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "REST_NAME")
    private String restName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "LOCATION")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LATITTUDE")
    private String latittude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LONGITUDE")
    private String longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CUISINE")
    private String cuisine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REVENUE")
    private double revenue;
    @ManyToMany(mappedBy = "restaurantList")
    private List<Promotions> promotionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restId")
    private List<Meal> mealList;
    @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ADMIN_ID")
    @ManyToOne(optional = false)
    private Administrator adminId;

    public Restaurant() {
    }

    public Restaurant(Integer restId) {
        this.restId = restId;
    }

    public Restaurant(Integer restId, String restName, String location, String latittude, String longitude, String cuisine, double revenue) {
        this.restId = restId;
        this.restName = restName;
        this.location = location;
        this.latittude = latittude;
        this.longitude = longitude;
        this.cuisine = cuisine;
        this.revenue = revenue;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatittude() {
        return latittude;
    }

    public void setLatittude(String latittude) {
        this.latittude = latittude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @XmlTransient
    public List<Promotions> getPromotionsList() {
        return promotionsList;
    }

    public void setPromotionsList(List<Promotions> promotionsList) {
        this.promotionsList = promotionsList;
    }

    @XmlTransient
    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

    public Administrator getAdminId() {
        return adminId;
    }

    public void setAdminId(Administrator adminId) {
        this.adminId = adminId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restId != null ? restId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.restId == null && other.restId != null) || (this.restId != null && !this.restId.equals(other.restId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities2.Restaurant[ restId=" + restId + " ]";
    }
    
}
