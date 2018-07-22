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
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findByCustId", query = "SELECT c FROM Customer c WHERE c.custId = :custId")
    , @NamedQuery(name = "Customer.findByFavoriteCuisine", query = "SELECT c FROM Customer c WHERE c.favoriteCuisine = :favoriteCuisine")
    , @NamedQuery(name = "Customer.findByUsername", query = "SELECT c FROM Customer c WHERE c.username = :username")
    , @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password")
    , @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address")
    , @NamedQuery(name = "Customer.findByCreditCard", query = "SELECT c FROM Customer c WHERE c.creditCard = :creditCard")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUST_ID")
    private Integer custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FAVORITE_CUISINE")
    private String favoriteCuisine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CREDIT_CARD")
    private String creditCard;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custId")
    private List<Orders> ordersList;

    public Customer() {
    }

    public Customer(Integer custId) {
        this.custId = custId;
    }

    public Customer(Integer custId, String favoriteCuisine, String username, String password, String address, String creditCard) {
        this.custId = custId;
        this.favoriteCuisine = favoriteCuisine;
        this.username = username;
        this.password = password;
        this.address = address;
        this.creditCard = creditCard;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getFavoriteCuisine() {
        return favoriteCuisine;
    }

    public void setFavoriteCuisine(String favoriteCuisine) {
        this.favoriteCuisine = favoriteCuisine;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities2.Customer[ custId=" + custId + " ]";
    }
    
}
