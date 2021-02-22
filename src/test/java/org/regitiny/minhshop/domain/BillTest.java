package org.regitiny.minhshop.domain;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;

class BillTest
{

  @Test
  void equalsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(Bill.class);
    Bill bill1 = new Bill();
    bill1.setId(1L);
    Bill bill2 = new Bill();
    bill2.setId(bill1.getId());
    assertThat(bill1).isEqualTo(bill2);
    bill2.setId(2L);
    assertThat(bill1).isNotEqualTo(bill2);
    bill1.setId(null);
    assertThat(bill1).isNotEqualTo(bill2);
  }
}
