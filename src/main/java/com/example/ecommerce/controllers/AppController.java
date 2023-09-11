package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Boardgame;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.FormCat;
import com.example.ecommerce.entities.FormGame;
import com.example.ecommerce.repositories.BoardgameRepository;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    BoardgameRepository gameRepo;

    @Autowired
    CategoryRepository catRepo;

    @GetMapping({"", "/gameshop", "/gameshop/", "/home"})
    public String listAll(Model model){

        List<Boardgame> gameList = gameRepo.findByScoreIsGreaterThan(1.9f);
        //TODO: fix this query
//        List<Category> catList = catRepo.findTopByBoardgameListIsGreaterThanOrderByBoardgameListDesc(1);
        List<Category> catList = catRepo.findAll();
        model.addAttribute("boardgames", gameList);
        model.addAttribute("categories", catList);
        return "home";
    }

    @GetMapping("/gameshop/categories")
    public String listCategories(Model model){

        List<Category> catList = catRepo.findAll();
        model.addAttribute("categories", catList);
        return "categories";
    }

    @GetMapping("/gameshop/categories/add")
    public String addCategory(FormCat formCat, Model model){
        model.addAttribute("formCat", formCat);
        return "addCategory";
    }

    @PostMapping("/gameshop/categories/add")
    public ModelAndView createCategory(FormCat formCat, ModelMap model){
        Category newCat = new Category();
        newCat.setName(formCat.getName());
        catRepo.save(newCat);
        List<Category> catList = catRepo.findAll();
        model.addAttribute("categories", catList);
        return new ModelAndView("redirect:/gameshop/categories", model);
    }

    @GetMapping("/gameshop/categories/{id}")
    public String listCategories(@PathVariable Long id, Model model){
        Category category = catRepo.findById(id).get();
        model.addAttribute("category", category);
        List<Boardgame> gameList = gameRepo.findByCategory_Id(id);
        model.addAttribute("boardgames", gameList);
        return "singleCategory";
    }

    @GetMapping("/gameshop/categories/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable Long id, ModelMap model){
        catRepo.deleteById(id);
        return new ModelAndView("redirect:/gameshop/categories", model);
    }

    @GetMapping("/gameshop/games/delete/{id}")
    public ModelAndView deleteGame(@PathVariable Long id, ModelMap model){

        Long idCat = gameRepo.findById(id).get().getCategoryId();
        String redirect = "redirect:/gameshop/categories/"+idCat;
        gameRepo.deleteById(id);
        return new ModelAndView(redirect, model);
    }

    @GetMapping("/gameshop/categories/{id}/addGame/")
    public String addGame(@PathVariable Long id, FormGame formGame, Model model){
        Category category = catRepo.findById(id).get();
        model.addAttribute("formGame", formGame);
        model.addAttribute("category", category);
        return "addGame";
    }

    @PostMapping("/gameshop/categories/{id}/addGame")
    public ModelAndView createGame(@PathVariable Long id, @ModelAttribute FormGame formGame, ModelMap model){
        Boardgame newGame = new Boardgame();
        newGame.setName(formGame.getName());
        newGame.setCategory(catRepo.getById(id));
        newGame.setPrice(formGame.getPrice());
        newGame.setScore(formGame.getScore());
        newGame.setDescription(formGame.getDescription());
        newGame.setPublisher(formGame.getPublisher());
        gameRepo.save(newGame);
        List<Boardgame> gameList = gameRepo.findByCategory_Id(id);
        model.addAttribute("boardgames", gameList);
        return new ModelAndView("redirect:/gameshop/categories/{id}", model);
    }

    @GetMapping("/gameshop/games/{id}")
    public String getOneGame(@PathVariable Long id, Model model){

        Boardgame game = gameRepo.findById(id).get();
        model.addAttribute("boardgame", game);
        return "game";
    }

}
