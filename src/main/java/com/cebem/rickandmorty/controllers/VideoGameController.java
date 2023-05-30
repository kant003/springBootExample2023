package com.cebem.rickandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.rickandmorty.services.VideoGameService;

@RestController
@CrossOrigin( origins = "*" )
public class VideoGameController {
    
    @Autowired
    VideoGameService videoGameService;

    @GetMapping("/api/videogames")
    public String videogames(){
        //TODO terminar !!!!!
        return videoGameService.getAll().toString();
    }

}
