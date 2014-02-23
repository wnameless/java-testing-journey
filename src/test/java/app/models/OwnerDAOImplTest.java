package app.models;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Test;

public class OwnerDAOImplTest {

  OwnerDAOImpl dao = new OwnerDAOImpl();
  SessionFactory sf;
  OwnerBean bean;

  @Before
  public void setUp() throws Exception {
    bean = new OwnerBean();
    bean.setFirstName("John");
    bean.setLastName("Doe");
    bean.setSsn("123-456-7890");
    sf =
        new AnnotationConfiguration().configure("hibernate.cfg.xml")
            .buildSessionFactory();
    dao.setSessionFactory(sf);
  }

  @Test
  public void testSave() {
    Session s = sf.getCurrentSession();
    s.beginTransaction();
    dao.save(bean);
    s.getTransaction().commit();
  }

  @Test
  public void testFindAll() {
    Session s = sf.getCurrentSession();
    s.beginTransaction();
    dao.save(bean);
    s.beginTransaction();
    assertEquals(1, dao.findAll().size());
    assertEquals(bean, dao.findAll().get(0));
    s.getTransaction().commit();
  }

}
