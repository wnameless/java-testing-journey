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
