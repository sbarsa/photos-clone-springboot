package com.sergiubarsa.photosclone;

import org.springframework.data.repository.ListCrudRepository;

public interface PhotosRepository extends ListCrudRepository<Photo, Integer> {
}
