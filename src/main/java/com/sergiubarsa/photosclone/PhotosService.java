package com.sergiubarsa.photosclone;


import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class PhotosService {

    private final PhotosRepository repository;

    public PhotosService(PhotosRepository repository) {
        this.repository = repository;
    }


    public Collection<Photo> get() {
        return repository.findAll();
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        repository.save(photo);
        return photo;
    }

    public void remove(int id) {
        repository.deleteById(id);
    }

    public Photo get(int id) {
        return repository.findById(id).orElse(null);
    }
}
