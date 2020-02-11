package com.example.webservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
    //first way of versioning(giving different url) using by twitter
    @GetMapping(path = "v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(path = "v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
    //second way of versioning(sending version as param) using by Amazon
    @GetMapping(value = "person/param",params = "version=1")
    public PersonV1 personV11(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(value = "person/param",params = "version=2")
    public PersonV2 personV22(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
    //third way of versioning(sending version in header) using by microsoft
    @GetMapping(value = "person/header",headers = "X-API-VERSION=1")
    public PersonV1 personV111(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(value = "person/header",headers = "X-API-VERSION=2")
    public PersonV2 personV222(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
    //fourth way of versioning(sending as producer)/mine type versioning/media versioning using by github
    @GetMapping(value = "person/produces",produces = "application/v1+json")
    public PersonV1 personV1111(){
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(value = "person/produces",produces = "application/v2+json")
    public PersonV2 personV2222(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
}
