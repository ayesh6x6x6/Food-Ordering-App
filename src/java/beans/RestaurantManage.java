/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import entities.Meal;
import entities.Orders;
import entities.Promotions;
import entities.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ayesh
 */
@ManagedBean(name="rests")
@SessionScoped
public class RestaurantManage {
    List<Restaurant> restlist;
    Restaurant selectedrest;
    String selectedrestname;
    Orders orders;
    List<Meal> meallist;
    double totalprice;    
    String paymentmethod;
    double discountPrice;
    
    
    public RestaurantManage(){
        selectedrest=new Restaurant();
        orders = new Orders();
        meallist = new ArrayList<>();
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
    
    
    public void addOrder(){
        orders.setPaymentOption(paymentmethod);
        EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
        em.getTransaction().begin();
        em.persist(orders);
        em.getTransaction().commit();
    }
    
    public List<Meal> getMeallist() {
        return meallist;
    }

    public void setMeallist(List<Meal> meallist) {
        this.meallist = meallist;
    }

    public double getTotalprice() {
        for(Meal m:meallist){
            totalprice+=m.getPrice();
            discountPrice+=(m.getMealPromo().getPriceDiscount()/100)*m.getPrice();
        }
        return totalprice-discountPrice;
    }

    public void setTotalprice(double totalprice) {
        
        this.totalprice = totalprice;
    }
    
    
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    
    public void removeItem(Meal m){
        orders.getMealList().remove(m);
    }
    public void setMealList(Meal m, Customer c){
//        System.out.println("HAHAHAHHAHAHAHAAHA");
        System.out.println(c.getUsername());
        System.out.println(m.getMealName());
        orders.setCustId(c);
        meallist.add(m);
        orders.setMealList(meallist);
//        System.out.println("DADADAD" + orders.getCustId().getUsername());
    }

    public String getSelectedrestname() {
        return selectedrestname;
    }

    public void setSelectedrestname(String selectedrestname) {
        this.selectedrestname = selectedrestname;
    }
    
    
//    public void setSelectedRest(Restaurant r){
//        System.out.println(selectedrestname);
//        System.out.println("HIIIIIIIIIIIIIIIIIIIIIII");
//        EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
//        Query q = em.createNamedQuery("Restaurant.findByRestName");
//        q.setParameter("restName", selectedrestname);
//        selectedrest = (Restaurant)q.getSingleResult();
//    }

    public Restaurant getSelectedrest() {
        return selectedrest;
    }

    public void setSelectedrest(Restaurant selectedrest) {
        this.selectedrest = selectedrest;
    }
    
    

    public List<Restaurant> getRestlist() {
        EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
        Query q = em.createNamedQuery("Restaurant.findAll");
        restlist = q.getResultList();        
        return restlist;
    }

    public void setRestlist(List<Restaurant> restlist) {
        this.restlist = restlist;
    }
    
}
