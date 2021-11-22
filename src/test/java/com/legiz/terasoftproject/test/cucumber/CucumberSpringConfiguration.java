package com.legiz.terasoftproject.test.cucumber;

import com.legiz.terasoftproject.TerasoftProjectApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = TerasoftProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TerasoftProjectApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
