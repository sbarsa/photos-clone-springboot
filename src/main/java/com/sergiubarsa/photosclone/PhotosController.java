package com.sergiubarsa.photosclone;


import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PhotosController {


    private Map<String, Photo> db = Map.of("1", new Photo("1","hello.jpg"));

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {

        return Collections.unmodifiableCollection(db.values());
    }


    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }

        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id) {

        db.remove(id);
    }
}
