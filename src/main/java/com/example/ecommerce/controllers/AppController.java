package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Boardgame;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.repositories.BoardgameRepository;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/gameshop")
public class AppController {

    @Autowired
    BoardgameRepository gameRepo;
    @Autowired
    CategoryRepository categoryRepo;

    @GetMapping("/")
    public ResponseEntity<Optional> listAll(){

        return null;
    }

    @GetMapping("/categories")
    public ResponseEntity<Optional> listCategories(){
        List<Category> categoryList = categoryRepo.findAll();
        return null;
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Boardgame> getOneGame(@PathVariable Long id){

        //TODO : fix the fetch type so the app doesn't crash (out of memory)
/*        Optional<Boardgame> gameQuery = gameRepo.findById(id);

        if (gameQuery.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(gameQuery.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }*/
    }

}
