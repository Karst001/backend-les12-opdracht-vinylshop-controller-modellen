package nl.novi.vinylshop.controllers;

import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.helpers.UrlHelper;
import nl.novi.vinylshop.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final UrlHelper urlHelper;

    //constructor
    public GenreController(GenreService genreService, UrlHelper urlHelper) {
        this.genreService = genreService;
        this.urlHelper = urlHelper;
    }

    //mappings
    @GetMapping
    public ResponseEntity<List<GenreEntity>> findAllGenres() {
        List<GenreEntity> genres = genreService.findAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenreEntity> findGenreById(@PathVariable Long id) {
        GenreEntity genre = genreService.findGenreById(id);
        if (genre != null) {  //prepare proper response, provided id may not exist
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }
        return new ResponseEntity<>(genre, HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genreInput) {
        GenreEntity newGenre = genreService.createGenre(genreInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.getId())).body(newGenre);
    }


    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable Long id, @RequestBody GenreEntity genreInput) {
        GenreEntity updated = genreService.updateGenre(id, genreInput);
        if (updated != null) {  //prepare proper response, provided id may not exist
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(updated, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
