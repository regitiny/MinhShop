package org.regitiny.minhshop.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.regitiny.minhshop.MinhShopApp;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = MinhShopApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration
{
}
