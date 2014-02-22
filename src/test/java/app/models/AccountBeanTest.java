package app.models;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToStringExcluding;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

public class AccountBeanTest {

  @Test
  public void testBean() {
    assertThat(
        AccountBean.class,
        allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(),
            hasValidBeanToStringExcluding("id")));
  }

  @Test
  public void testEquaslToAndHashCode() {
    EqualsVerifier.forClass(AccountBean.class)
        .suppress(Warning.NONFINAL_FIELDS).verify();
  }

}
