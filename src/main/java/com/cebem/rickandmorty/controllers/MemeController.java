package com.cebem.rickandmorty.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.rickandmorty.models.MemeModel;
import com.cebem.rickandmorty.services.MemeService;

@Controller
public class MemeController {
    @Autowired
    MemeService memeService;

    @RequestMapping("/memes")
    public String memes(Model model){
        ArrayList<MemeModel> memes = memeService.getAllMemes();
        model.addAttribute("misMemes", memes);
        return "memeList";
    } 

    @PostMapping("/memes")
    public String memesAdd(@RequestParam Map<String, String> body){
        String description = body.get("description");
        String url = body.get("url");
        String category = body.get("category");
        String author = body.get("author");

        MemeModel nuevoMeme = new MemeModel();
        nuevoMeme.setDescription(description);
        nuevoMeme.setUrl(url);
        nuevoMeme.setCategory(category);
        nuevoMeme.setAuthor(author);

        memeService.createMeme(nuevoMeme);
        return "memeOK";
    }

    @RequestMapping("/memes/addForm")
    public String memeAddForm(){
        return "memeAddForm";
    }  

}
