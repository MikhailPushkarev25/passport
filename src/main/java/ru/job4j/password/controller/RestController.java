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

    @Value("${save}")
    private String save;
    @Value("${id}")
    private String numId;
    @Value("${update}")
    private String update;
    @Value("${delete}")
    private String delete;
    @Value("${find}")
    private String find;
    @Value("${seria}")
    private String seria;
    @Value("${unavailable}")
    private String unavailable;
    @Value("${replaceable}")
    private String replaceable;

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        Passport ps = rest.postForObject(save, passport, Passport.class);
        return new ResponseEntity<Passport>(
                ps,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> findById(@PathVariable int id) {
        rest.getForObject(numId, Passport.class, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        rest.put(update, passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(delete, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return rest.exchange(
                find,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {}
        ).getBody();
    }

    @GetMapping("/find")
    public List<Passport> findBySeries(@RequestParam int series) {
        return rest.exchange(
                seria,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { },
                series
        ).getBody();
    }

    @GetMapping("/unavailable")
    public List<Passport> findUnAvailable() {
        return rest.exchange(
                unavailable,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }

    @GetMapping("/replaceable")
    public List<Passport> findReplaceable() {
        return rest.exchange(
                replaceable,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() { }
        ).getBody();
    }
}
