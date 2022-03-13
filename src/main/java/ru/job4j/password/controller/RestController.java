package ru.job4j.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String SAVE = "http://localhost:8080/passport/save/";
    private static final String ID = "http://localhost:8080/passport/{id}";
    private static final String UPDATE = "http://localhost:8080/passport/update/";
    private static final String DELETE = "http://localhost:8080/passport/delete/{id}";
    private static final String FIND = "http://localhost:8080/passport/findAll/";
    private static final String SERIA = "http://localhost:8080/passport/find?seria={seria}";
    private static final String UNAVAILABLE = "http://localhost:8080/passport/unavailable/";
    private static final String REPLACEABLE = "http://localhost:8080/passport/replaceable/";

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        Passport ps = rest.postForObject(SAVE, passport, Passport.class);
        return new ResponseEntity<Passport>(
                ps,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> findById(@PathVariable int id) {
        rest.getForObject(ID, Passport.class, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        rest.put(UPDATE, passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(DELETE, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return rest.exchange(
                FIND,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {}
        ).getBody();
    }

    @GetMapping("/find")
    public List<Passport> findBySeries(@RequestParam int seria) {
        return rest.exchange(
                SERIA,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { },
                seria
        ).getBody();
    }

    @GetMapping("/unavailable")
    public List<Passport> findUnAvailable() {
        return rest.exchange(
                UNAVAILABLE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }

    @GetMapping("/replaceable")
    public List<Passport> findReplaceable() {
        return rest.exchange(
                REPLACEABLE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }
}
