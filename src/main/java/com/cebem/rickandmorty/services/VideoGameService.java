package com.cebem.rickandmorty.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebem.rickandmorty.models.VideoGameModel;
import com.cebem.rickandmorty.repositories.VideoGameRepository;



@Service
public class VideoGameService {
    @Autowired
    VideoGameRepository videoGameRepository;

    public ArrayList<VideoGameModel> getAll(){
        return (ArrayList<VideoGameModel>) videoGameRepository.findAll();
    }
    public VideoGameModel getById(long id){
        if(videoGameRepository.findById(id).isPresent())
            return videoGameRepository.findById(id).get();
        else
            return null;
    }
    public VideoGameModel create(VideoGameModel videoGame){
        return videoGameRepository.save(videoGame);
    }
    public boolean delete(long id){
        try{
            videoGameRepository.deleteById(id);
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
    }

}
