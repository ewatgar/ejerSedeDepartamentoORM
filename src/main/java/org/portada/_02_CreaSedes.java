package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Sede;

public class _02_CreaSedes {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");

            Transaction t = null;
            try {
                t = s.beginTransaction();
                Sede s1 = new Sede("sede1");
                Sede s2 = new Sede("sede2");
                //Se insertan dos sedes
                s.persist(s1);
                s.persist(s2);
                t.commit();

            } catch (Exception e) {
                e.printStackTrace();
                if (t != null) {
                    System.out.println("ERROR: ROLLBACK");
                    t.rollback();
                }
            }



        }
    }
}
