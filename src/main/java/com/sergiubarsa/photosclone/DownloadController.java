package com.sergiubarsa.photosclone;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Photo photo = photosService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        headers.setContentDisposition(ContentDisposition.inline().filename(photo.getFileName()).build());

        return new ResponseEntity<>(photo.getData(), headers, HttpStatus.OK);
    }
}
