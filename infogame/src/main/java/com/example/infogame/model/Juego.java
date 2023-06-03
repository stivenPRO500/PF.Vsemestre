package com.example.infogame.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego")
    private Integer id;
    @NotBlank
    private  String titulo;
    @NotBlank
    private String sinopsis;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;
    @NotBlank
    private String youtubeTrailerId;
    private String rutaPortada;
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "generojuego",joinColumns = @JoinColumn(name = "idjuego"))
    private List<Genero> generos;

    @Transient
    private MultipartFile portada;
}
