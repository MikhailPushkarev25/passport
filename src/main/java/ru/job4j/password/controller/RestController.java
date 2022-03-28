package ru.job4j.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.password.model.Passport;

import java.util.List;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/external")
public class RestController {

    @Autowired
    private RestTemplate rest;

    @Value("${api-uri}")
    private String api_uri;

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        Passport ps = rest.postForObject(api_uri + "/save", passport, Passport.class);
        return new ResponseEntity<Passport>(
                ps,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> findById(@PathVariable int id) {
        rest.getForObject(api_uri + "/{id}", Passport.class, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        rest.put(api_uri + "/update", passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        rest.delete(api_uri + "/delete?id={id}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return rest.exchange(
                api_uri + "/findAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {}
        ).getBody();
    }

    @GetMapping("/find")
    public List<Passport> findBySeries(@RequestParam int series) {
        return rest.exchange(
                api_uri + "/find?series={series}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { },
                series
        ).getBody();
    }

    @GetMapping("/unavailable")
    public List<Passport> findUnAvailable() {
        return rest.exchange(
                api_uri + "/unavailable",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }

    @GetMapping("/replaceable")
    public List<Passport> findReplaceable() {
        return rest.exchange(
                api_uri + "/replaceable",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }
}
