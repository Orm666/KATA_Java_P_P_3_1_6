package org.example;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Communication {
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    public void getCode() {
        String URL = "http://94.198.50.185:7081/api/users";

        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        headers.add(HttpHeaders.COOKIE, responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE));

        HttpEntity<User> requestBody1 = new HttpEntity<>(new User(3L, "James", "Brown", (byte) 10), headers);
        System.out.print(restTemplate.exchange(URL, HttpMethod.POST, requestBody1, String.class).getBody());

        HttpEntity<User> requestBody2 = new HttpEntity<>(new User(3L, "Thomas", "Shelby", (byte) 10), headers);
        System.out.print(restTemplate.exchange(URL, HttpMethod.PUT, requestBody2, String.class).getBody());

        HttpEntity<User> requestEntity3 = new HttpEntity<>(headers);
        System.out.println(restTemplate.exchange(URL + "/3", HttpMethod.DELETE, requestEntity3, String.class).getBody());
    }

}
