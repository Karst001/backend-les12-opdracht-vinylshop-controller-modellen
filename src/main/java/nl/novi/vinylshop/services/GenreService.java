package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    public GenreEntity findGenreById(Long id) {
        GenreEntity result = getGenreById(id);

        if (result != null) {
            return genreRepository.findById(id).orElse(null);
        }
        return null;
    }

    public GenreEntity createGenre(GenreEntity genre) {
        return genreRepository.save(genre);
    }

    public GenreEntity updateGenre(Long id, GenreEntity genre) {
        GenreEntity result = (getGenreById(id));
        if (result != null) {
            // Update fields only, not the id
            result.setName(genre.getName());
            result.setDescription(genre.getDescription());

            //save to database
            return genreRepository.save(result);
        }
        return null;
    }


    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }


    //private function to check if record exists
    private GenreEntity getGenreById(Long id) {
        // First check if record exists in database
        Optional<GenreEntity> genreEntityOptional = genreRepository.findById(id);

        if(genreEntityOptional.isPresent()){
            return genreEntityOptional.get();  //return the found Entity
        } else {
            return null;
        }
    }
}
