package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Boardgame;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.repositories.BoardgameRepository;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/gameshop")
public class AppController {

    @Autowired
    BoardgameRepository gameRepo;

    @Autowired
    CategoryRepository catRepo;

    @GetMapping({"", "/home"})
    public String listAll(Model model){

        List<Boardgame> gameList = gameRepo.findByScoreIsGreaterThan(1.9f);
        //TODO: fix this query
//        List<Category> catList = catRepo.findTopByBoardgameListIsGreaterThanOrderByBoardgameListDesc(1);
        List<Category> catList = catRepo.findAll();
        model.addAttribute("boardgames", gameList);
        model.addAttribute("categories", catList);
        return "home";
    }

    @GetMapping("/categories")
    public String listCategories(Model model){

        List<Category> catList = catRepo.findAll();
        model.addAttribute("categories", catList);
        return "categories";
    }

    @PostMapping("/categories/add")
    public String createCategory(Category formCat, Model model){

        catRepo.save(formCat);
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String listCategories(@PathVariable Long id, Model model){
        Category category = catRepo.findById(id).get();
        model.addAttribute("category", category);
        List<Boardgame> gameList = gameRepo.findByCategory_Id(id);
        model.addAttribute("boardgames", gameList);
        return "singleCategory";
    }

    @GetMapping("/games/{id}/delete")
    public ModelAndView deleteGame(@PathVariable Long id, ModelMap model){

        Long idCat = gameRepo.findById(id).get().getCategoryId();
        String redirect = "redirect:/gameshop/categories/"+idCat;
        gameRepo.deleteById(id);
        return new ModelAndView(redirect, model);
    }

    @PostMapping("/categories/{id}/add")
    public ModelAndView createGame(@PathVariable Long id, Boardgame formGame, ModelMap model){

        gameRepo.save(formGame);
        List<Boardgame> gameList = gameRepo.findByCategory_Id(id);
        model.addAttribute("boardgames", gameList);
        return new ModelAndView("redirect:/gameshop/categories/{id}", model);
    }

    @GetMapping("/games/{id}")
    public String getOneGame(@PathVariable Long id, Model model){

        Boardgame game = gameRepo.findById(id).get();
        model.addAttribute("boardgame", game);
        return "game";
    }

}
