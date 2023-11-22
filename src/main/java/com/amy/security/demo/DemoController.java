package com.amy.security.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/demo-controller")
@RestController
@RequiredArgsConstructor
public class DemoController {

    @GetMapping()
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hi from a secure endpoint");
    }
        


}
