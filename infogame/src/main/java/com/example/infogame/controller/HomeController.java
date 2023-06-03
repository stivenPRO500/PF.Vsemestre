package com.example.infogame.controller;

import com.example.infogame.model.Juego;
import com.example.infogame.repo.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private JuegoRepository juegoRepository;
    @GetMapping("")
    ModelAndView index(){
        List<Juego> ultimosJuegos = juegoRepository
                .findAll(PageRequest.of(0,4, Sort.by("fechaEstreno").descending()))
                .toList();
        return new ModelAndView("index")
                .addObject("ultimosJuegos",ultimosJuegos);

    }
    @GetMapping("/juegos")
    ModelAndView listaJuegos(@PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.DESC)
                             Pageable pageable){
        Page<Juego> juegos= juegoRepository.findAll(pageable);
                return new ModelAndView("juegos")
                        .addObject("juegos",juegos);
    }
    @GetMapping("/juegos/{id}")
    ModelAndView detallesJuegos(@PathVariable Integer id){
        Juego juego= juegoRepository.getOne(id);
        return new ModelAndView("juego")
                .addObject("juego",juego);
    }
}
