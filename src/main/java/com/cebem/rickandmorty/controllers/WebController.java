package com.cebem.rickandmorty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.services.RickAndMortyService2;

@Controller
public class WebController {

  @Autowired
  RickAndMortyService2 rickAndMortyService2;

  @RequestMapping("/rickandmorty/allTemplate")
  public String charactersTemplate(Model modelo) {
    CharactersModel charactersModel = rickAndMortyService2.getAllCharacters();
    
    modelo.addAttribute("creator", "Creado por Angel");
    modelo.addAttribute("characters", charactersModel.results);
    return "rickandmortyall";
  }

  // Queremos mostrar en una página web el total de personajes que tenemos
  // Aparecerá la frase TOTAL DE PERSONAJE:   
  // Centrado en pantalla y de color verde.
  // Usa un motor de plantallas (thymeleaf)
  @RequestMapping("/rickandmorty/count")
  public String charactersCountTemplate(Model modelo) {
    int count = rickAndMortyService2.getCharactersCount();
    modelo.addAttribute("count", count);
    return "rickandmortyCount";
  }
}
