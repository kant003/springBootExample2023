package com.cebem.rickandmorty.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cebem.rickandmorty.models.VideoGameModel;

@Repository
public interface VideoGameRepository extends CrudRepository<VideoGameModel, Long> {
    
}
