package info.joninousiainen.exercise.controller;

import info.joninousiainen.exercise.App;
import info.joninousiainen.exercise.repo.StringSetRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest(randomPort = true)
public abstract class ControllerTest {
    @Value("${local.server.port}")
    protected int port;
    @Autowired
    protected StringSetRepository stringSetRepository;

    protected RestTemplate restTemplate = new TestRestTemplate();
}
