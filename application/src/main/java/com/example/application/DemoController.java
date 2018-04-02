package com.example.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/demo")
public class DemoController {

    private DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/cached")
    public ResponseEntity getCachedData(){
        return new ResponseEntity(demoService.getCachedData(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getData(){
        return new ResponseEntity(demoService.getData(), HttpStatus.OK);
    }

}
