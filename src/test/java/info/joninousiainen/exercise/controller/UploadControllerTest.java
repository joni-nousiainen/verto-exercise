package info.joninousiainen.exercise.controller;

import info.joninousiainen.exercise.App;
import info.joninousiainen.exercise.model.StringSet;
import info.joninousiainen.exercise.repo.StringSetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest(randomPort = true)
public class UploadControllerTest {
    @Value("${local.server.port}")
    private int port;
    private RestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private StringSetRepository stringSetRepository;

    @Test
    public void FourSimpleStrings() {
        ResponseEntity<String> response = upload("one two\nthree\tfour");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody());

        Set<String> strings = stringSetRepository.findOne(1l).getStrings();
        assertEquals(4, strings.size());
        assertThat(strings, hasItems("one", "two", "three"));
    }

    private ResponseEntity<String> upload(String request) {
        return restTemplate.postForEntity("http://localhost:" + port + "/upload", request, String.class);
    }
}
