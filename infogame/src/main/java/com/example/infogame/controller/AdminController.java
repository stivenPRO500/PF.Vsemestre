package com.example.infogame.controller;

import com.example.infogame.model.Genero;
import com.example.infogame.model.Juego;
import com.example.infogame.repo.GeneroRepository;
import com.example.infogame.repo.JuegoRepository;
import com.example.infogame.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private JuegoRepository juegoRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    @GetMapping("")
    ModelAndView index(@PageableDefault(sort = "titulo", size = 5) Pageable pageable){

        Page<Juego>juegos = juegoRepository.findAll(pageable);
        return new ModelAndView("admin/index")
                .addObject("juegos",juegos);

    }
    @GetMapping("/juegos/nuevo")
    ModelAndView nuevoJuego(){
        List<Genero> generos= generoRepository.findAll(Sort.by("titulo"));
        return new ModelAndView("admin/nuevo-juego")
                .addObject("juego",new Juego())
                .addObject("generos",generos);
    }
    @PostMapping("/juegos/nuevo")
    ModelAndView crearJuego(@Validated Juego juego, BindingResult bindingResult){
        if (bindingResult.hasErrors() || juego.getPortada().isEmpty()) {
            if (juego.getPortada().isEmpty()) {
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }
            List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/nuevo-juego")
                    .addObject("juego", juego)
                    .addObject("generos", generos);
        }
        String rutaPortada=fileSystemStorageService.storage(juego.getPortada());
        juego.setRutaPortada(rutaPortada);
    juegoRepository.save(juego);
        return new ModelAndView("redirect:/admin");
    }
    @GetMapping("/juegos/{id}editar")
    ModelAndView editarJuego(@PathVariable Integer id){
         Juego juego = juegoRepository.getOne(id);
         List<Genero> generos= generoRepository.findAll(Sort.by("titulo"));

         return new ModelAndView("admin/editar-juego")
                 .addObject("juego",juego)
                 .addObject("generos",generos);
    }
    @SuppressWarnings("deprecation")
    @PostMapping("/juegos/{id}/editar")
    ModelAndView actualizarJuego(@PathVariable Integer id,
                                 @Validated Juego juego, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<Genero> generos= generoRepository.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/editar-juego")
                    .addObject("juegos",juego)
                    .addObject("generos",generos);
        }
        Juego juegoFromDb = juegoRepository.getOne(id);
        juegoFromDb.setTitulo(juego.getTitulo());
        juegoFromDb.setSinopsis(juego.getSinopsis());
        juegoFromDb.setFechaEstreno(juego.getFechaEstreno());
        juegoFromDb.setYoutubeTrailerId(juego.getYoutubeTrailerId());
        juegoFromDb.setGeneros(juego.getGeneros());

        if (!juego.getPortada().isEmpty()){
            fileSystemStorageService.delete(juegoFromDb.getRutaPortada());
            String rutaPortada = fileSystemStorageService.storage(juego.getPortada());
            juegoFromDb.setRutaPortada(rutaPortada);
        }
        juegoRepository.save(juegoFromDb);
        return new ModelAndView("redirect:/admin");

    }
    @PostMapping ("/juegos/{id}/eliminar")
    String eliminarJuego(@PathVariable Integer id){
        Juego juego = juegoRepository.getOne(id);
        juegoRepository.delete(juego);
        fileSystemStorageService.delete(juego.getRutaPortada());
        return "redirect:/admin";
    }



}
