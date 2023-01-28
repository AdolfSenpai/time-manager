package com.ed.timemanager.commons.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ed.timemanager.commons.utils.PomReader;

@RestController
@RequestMapping("/api")
public class CommonController {
    //region Public
    
    @GetMapping("/ping")
    public ResponseEntity<Void> ping() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/version")
    public ResponseEntity<String> version() {

        return new ResponseEntity<>(PomReader.getProjectVersion(), HttpStatus.OK);
    }

    //endregion
}
