package com.cebem.rickandmorty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.utils.Utils;

@Service
public class RickAndMortyService2 {
    @Autowired
    RestTemplate restTemplate;


    public CharacterModel getCharacterRandom(){
        final int TOTAL_CHARACTERS  = 826;
        int random = Utils.getRandomValue(TOTAL_CHARACTERS-1)+1;  // 1..826
        String url = "https://rickandmortyapi.com/api/character/"+random;
        //RestTemplate restTemplate = new RestTemplate();
        CharacterModel datos = restTemplate.getForObject(url, CharacterModel.class );
        return datos;
    }

    public CharactersModel getAllCharacters(){
        String url = "https://rickandmortyapi.com/api/character/";
        CharactersModel datos = restTemplate.getForObject(url, CharactersModel.class );
        return datos;
    }


}
