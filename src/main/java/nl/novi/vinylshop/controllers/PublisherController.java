package nl.novi.vinylshop.controllers;


import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    //constructor
    public PublisherController(PublisherService publisherService, UrlHelper urlHelper) {
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;
    }

    //mappings
    @GetMapping
    public ResponseEntity<List<PublisherEntity>> findAllPublishers() {
        List<PublisherEntity> publishers = publisherService.findAllPublishers();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PublisherEntity> findPublisherById(@PathVariable Long id) {
        PublisherEntity publisher = publisherService.findPublisherById(id);
        if (publisher != null) {  //prepare proper response, provided id may not exist
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        }
        return new ResponseEntity<>(publisher, HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<PublisherEntity> createPublisher(@RequestBody PublisherEntity publisherInput) {
        PublisherEntity newPublisher = publisherService.createPublisher(publisherInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherEntity> updatePubliser(@PathVariable Long id, @RequestBody PublisherEntity publisherInput) {
        PublisherEntity updated = publisherService.updatePublisher(id, publisherInput);
        if (updated != null) { //prepare proper response, provided id may not exist
            return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(updated, HttpStatus.NO_CONTENT);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
