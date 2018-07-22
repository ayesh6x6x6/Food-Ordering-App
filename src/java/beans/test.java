/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ayesh
 */
public class test {
        public static void main(String [] args){
        CustomerManage rc = new CustomerManage();
        Customer c = new Customer();
        c.setAddress("Deira");
        c.setCreditCard("1241204-1241");
        c.setFavoriteCuisine("Mediterranean");
        c.setPassword("rebin");
        c.setUsername("Rebin");
       
         EntityManager em = Persistence.createEntityManagerFactory("416Project2PU").createEntityManager();
         Query q = em.createNamedQuery("Customer.findByCustId");
         q.setParameter("custId", 1);
         Customer cu = (Customer) q.getSingleResult();
          System.out.println(cu);
    }
}
