package app.models;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class OwnerServiceImplTest {

  @Inject
  OwnerService service;

  @Inject
  OwnerDAOImpl dao;

  OwnerBean bean;

  @Before
  public void setUp() throws Exception {
    bean = new OwnerBean();
    bean.setFirstName("John");
    bean.setLastName("Doe");
    bean.setSsn("123-456-7890");
    service.addOwner("John", "Doe", "123-456-7890", null, null);
  }

  @Test
  public void testAddOwner() {
    assertEquals(1, dao.findAll().size());
    assertEquals(bean, dao.findAll().get(0));
  }

  @Test
  public void testGetOwners() {
    assertEquals(1, service.getAllOwners().size());
    assertEquals(bean, service.getAllOwners().get(0));
  }

  @Test
  public void testDeleteOwner() {
    OwnerBean bean = service.getAllOwners().get(0);
    service.deleteOwner(bean.getId());
    assertEquals(0, service.getAllOwners().size());
  }

}