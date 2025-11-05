package com.sergiubarsa.photosclone;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PhotosController {


    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));

    }};

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
    public Photo delete(@PathVariable String id) {
        Photo removedPhoto = db.remove(id);
        if (removedPhoto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return removedPhoto;
    }
}
