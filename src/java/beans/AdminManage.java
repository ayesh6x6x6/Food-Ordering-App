/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Administrator;
import entities.Customer;
import entities.Meal;
import entities.Promotions;
import entities.Restaurant;
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
@ManagedBean(name="admin")
@SessionScoped
public class AdminManage {
    public AdminManage(){
        admin = new Administrator();
        m=new Meal();
        r=new Restaurant();
        p=new Promotions();
    }
    private String pieData=null;
    Administrator admin;
    String loginresult;
    String revenue,adminId,discount,restId,mealprice,mealPromo;
    Meal m;
    Restaurant r;
    Promotions p;

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getMealprice() {
        return mealprice;
    }

    public void setMealprice(String mealprice) {
        this.mealprice = mealprice;
    }

    public String getMealPromo() {
        return mealPromo;
    }

    public void setMealPromo(String mealPromo) {
        this.mealPromo = mealPromo;
    }
    
    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    
    public Promotions getP() {
        return p;
    }

    public void setP(Promotions p) {
        this.p = p;
    }
    
    
    public Meal getM() {
        return m;
    }

    public void setM(Meal m) {
        this.m = m;
    }

    public Restaurant getR() {
        return r;
    }

    public void setR(Restaurant r) {
        this.r = r;
    }
    
    public void addMeal(){
         m.setPrice(Double.parseDouble(mealprice));
         EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
         if(mealPromo!=null){
             Query q=em.createNamedQuery("Promotions.findByPromoId");
             q.setParameter("promoId", Integer.parseInt(mealPromo));
             Promotions p2 = (Promotions)q.getSingleResult();
             m.setMealPromo(p2);
         }
         Query q2=em.createNamedQuery("Restaurant.findByRestId");
             q2.setParameter("restId", Integer.parseInt(restId));
             Restaurant r2 = (Restaurant)q2.getSingleResult();
             m.setRestId(r2);
         
         
         
         em.getTransaction().begin();
         em.persist(m);
         em.getTransaction().commit();
    }
    
    public void addPromotion(){
         p.setPriceDiscount(Double.parseDouble(discount));
         EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
         em.getTransaction().begin();
         em.persist(p);
         em.getTransaction().commit();
    }
    
    public void addRestaurant(){
         r.setRevenue(Double.parseDouble(revenue));        
         
         EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
         Query q=em.createNamedQuery("Administrator.findByAdminId");
         q.setParameter("adminId", Integer.parseInt(adminId));
         Administrator a = (Administrator)q.getSingleResult();
         r.setAdminId(a);
         em.getTransaction().begin();
         em.persist(r);
         em.getTransaction().commit();
    }
    
    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
    
      public String getLoginresult() {
        EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
        Query q = em.createQuery("select a from Administrator a where a.name = :name and a.password =:password" );
          q.setParameter("name", admin.getName());
          q.setParameter("password", admin.getPassword());
          try{
          admin=(Administrator)q.getSingleResult();
          loginresult="Welcome "+admin.getName();
          }
          catch(Exception e){
              loginresult="Not a valid login ID!";
          }
        return loginresult;
    }
    
    private void prepareData()
    {
        EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
        Query q = em.createNamedQuery("Restaurant.findAll");
        List l = q.getResultList();
        System.out.println(l.size());
        StringBuffer b = new StringBuffer();
        b.append("[ ['Restaurant','Revenue'] ");
        
        for(int i=0;i<l.size();i++)
        {
            b.append(",");
            Restaurant r = (Restaurant)l.get(i);
            String n = r.getRestName();
            double rev = r.getRevenue();
            b.append("[ '" + n + "' , "+rev+"]");
        }
       b.append(" ]");
       pieData = b.toString();
    }
        public String getPieData() {
        if(pieData==null)
            prepareData();
        return pieData;
        
    }
}
