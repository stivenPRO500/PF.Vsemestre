package com.example.infogame.controller;

import com.example.infogame.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("{/filename:.+}")
    Resource getResource(@PathVariable("filename")String filename){
        return fileSystemStorageService.loadAsResource(filename);
    }
}
