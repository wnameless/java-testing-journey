/**
 *
 * @author Wei-Ming Wu
 *
 *
 * Copyright 2014 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package app.models;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OwnerDAOImplTest {

  OwnerDAOImpl dao = new OwnerDAOImpl();
  OwnerBean bean;
  SessionFactory sf;
  Session s;

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
    s = sf.getCurrentSession();
    s.beginTransaction();
  }

  @After
  public void tearDown() throws Exception {
    s.getTransaction().commit();
  }

  @Test
  public void testSave() {
    dao.save(bean);
  }

  @Test
  public void testFindAll() {
    dao.save(bean);
    assertEquals(1, dao.findAll().size());
    assertEquals(bean, dao.findAll().get(0));
  }

}
