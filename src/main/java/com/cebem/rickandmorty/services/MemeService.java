package com.cebem.rickandmorty.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebem.rickandmorty.models.MemeModel;
import com.cebem.rickandmorty.repositories.MemeRepository;

@Service
public class MemeService {
    @Autowired
    MemeRepository memeRepository;

    public ArrayList<MemeModel> getAllMemes(){
        return (ArrayList<MemeModel>)memeRepository.findAll();
     }

    public MemeModel createMeme(MemeModel meme){
        return memeRepository.save(meme);
    }

    public boolean deleteMeme(long id){
        try{
            memeRepository.deleteById(id);
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
    }

    // ...
}
