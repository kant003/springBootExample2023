package com.cebem.rickandmorty.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebem.rickandmorty.models.CommentModel;
import com.cebem.rickandmorty.repositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public ArrayList<CommentModel> getAllByVideoGameId(long videoGameId){
        return commentRepository.findByVideogameIdOrderByCreatedAtDesc(videoGameId);
    }

    public CommentModel create(CommentModel comment){
        return commentRepository.save(comment);
    }

    public boolean delete(long id){
        try{
            commentRepository.deleteById(id);
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
    }
}
