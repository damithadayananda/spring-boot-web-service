package com.example.webservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class userResource {
    @Autowired
    private UserDaoService service;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<user>retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<user> retrieveUser(@PathVariable int id){
        //if
        Optional<user> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id-"+id);
        /**
         * There is a compatible issue with hateoas and springfox
         * so for the moment I'm going to remove hateoas
         */
        //hateoas part is for adding other use full links to response
//        EntityModel<user> model = new EntityModel<>(user);
//        WebMvcLinkBuilder linkOne = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        WebMvcLinkBuilder linkTwo = linkTo(methodOn(this.getClass()).createUser(user));
//        model.add(linkOne.withRel("all-users"),linkTwo.withRel("create-user"));
//        return model;

        return user;
    }
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody user user){
        user savedUser = userRepository.save(user);
        URI location =ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }
}
