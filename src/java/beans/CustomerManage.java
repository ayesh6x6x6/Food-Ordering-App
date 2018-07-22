/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ayesh
 */
@ManagedBean(name="regcus")
@SessionScoped
public class CustomerManage {
    private Customer c;
    private List<Customer> culist;
    private String loginresult;
    
    public CustomerManage(){
        c=new Customer();
    }
    
    public Customer getC() {
        return c;
    }

    public String getLoginresult() throws NoSuchAlgorithmException {
        EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
        Query q = em.createQuery("select c from Customer c" );
        List<Customer> cuslist=q.getResultList();
        for(Customer cust:cuslist){
            
            MessageDigest md = MessageDigest.getInstance("MD5");
           
            System.out.println(cust.getUsername()+"::"+cust.getPassword());
            
            String pass = new String(md.digest(c.getPassword().getBytes()));
            System.out.println("input pass"+pass);
            if(c.getUsername().equals(cust.getUsername())&&pass.equals(cust.getPassword())){                

          loginresult="Welcome "+c.getUsername();
            }
          else{
              loginresult="Not a valid login ID!";
                  }
            }
        

          
        return loginresult;
    }

    public void setLoginresult(String loginresult) {
        this.loginresult = loginresult;
    }
    
    
    
    public List<Customer> getCulist() {
         EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
          Query q = em.createNamedQuery("Customer.findAll");
          culist= q.getResultList();
            return culist;
    }

    public void setC(Customer c) {
        this.c = c;
    }
    
    public void registerCustomer() throws NoSuchAlgorithmException{
         MessageDigest md = MessageDigest.getInstance("MD5");
         c.setPassword(new String(md.digest(c.getPassword().getBytes())));
         EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
         em.getTransaction().begin();
         em.persist(c);
         em.getTransaction().commit();
    }
    

}
