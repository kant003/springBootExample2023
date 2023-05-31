
package com.cebem.rickandmorty.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cebem.rickandmorty.models.CommentModel;
import com.cebem.rickandmorty.models.VideoGameModel;
import com.cebem.rickandmorty.services.CommentService;
import com.cebem.rickandmorty.services.VideoGameService;


@Controller
public class VideoGameWebController {
    @Autowired
    VideoGameService videoGameService;

    @Autowired
    CommentService commentService;

@RequestMapping("/game/{id}")
  public String gameTemplate(Model modelo, @PathVariable String id){
    VideoGameModel game = videoGameService.getById(Long.parseLong(id));
    ArrayList<CommentModel> comments = commentService.getAllByVideoGameId(Long.parseLong(id));
    modelo.addAttribute("game", game);
    modelo.addAttribute("comments", comments);

    return "game";
  }


  @RequestMapping("/addgame")
  public String addGame(){
    return "gameAddForm";
  }

  @RequestMapping("/games")
  public String videoGames(Model modelo){
    ArrayList<VideoGameModel> games = videoGameService.getAll();
    modelo.addAttribute("games", games);
    return "games";
  }

}