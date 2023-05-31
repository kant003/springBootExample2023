package com.cebem.rickandmorty.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cebem.rickandmorty.models.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    // listar todos los comentario de un videojuego en particular, ordenador por createdAt
    ArrayList<CommentModel> findByVideogameIdOrderByCreatedAtDesc(Long videogameId);
}
