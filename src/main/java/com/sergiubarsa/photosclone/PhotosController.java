package com.sergiubarsa.photosclone;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotosController {

    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {

        return photosService.get();
    }


    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photosService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public Photo delete(@PathVariable String id) {
        Photo removedPhoto = photosService.remove(id);
        if (removedPhoto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return removedPhoto;
    }

    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.save(file.getOriginalFilename(), file.getBytes());
    }

}
