package com.cebem.rickandmorty.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="meme")
public class MemeModel {
    long id;
    String category;
    String url;
    String description;
    String author;
    Date createDate;
}
