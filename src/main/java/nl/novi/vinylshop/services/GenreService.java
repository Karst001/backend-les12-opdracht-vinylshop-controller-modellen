package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.mappers.GenreDTOMapper;
import nl.novi.vinylshop.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    //dependency injections
    private final GenreRepository genreRepository;
    private final GenreDTOMapper genreDTOMapper;

    public GenreService(GenreRepository genreRepository, GenreDTOMapper genreDTOMapper) {
        //instantiate
        this.genreRepository = genreRepository;
        this.genreDTOMapper = genreDTOMapper;
    }


    public List<GenreResponseDTO> findAllGenres() {
        //return the GenreResponseDTO via the DTO mapper to the controller
        return genreDTOMapper.mapToDto(genreRepository.findAll());

        //optional
        //return genreDTOMapper.mapToDto(genreRepository.findAllByOrderByIdAsc());  return the data sorted as Jpa doesn't do that by default
        //in the GenreRepository there is a custom function added
    }

    public GenreResponseDTO findGenreById(Long id) {
        //check if records exists
        GenreEntity result = getGenreEntityById(id);  //this step can be skipped when using stored procedures

        //return the entity via the mapper
        if (result != null) {
            return genreDTOMapper.mapToDto(result);
        }
        return null;
    }


    public GenreResponseDTO createGenre(GenreRequestDTO genreDTO) {
        //map the genreDTO to the genreEntity
        GenreEntity result = genreDTOMapper.mapToEntity(genreDTO);

        //call the repo and save the result
        result = genreRepository.save(result);

        //return the genreEntity via the DTO mapper to the controller
        return genreDTOMapper.mapToDto(result);
    }


    public GenreResponseDTO updateGenre(Long id, GenreRequestDTO requestDto) {
        //check if records exists
        GenreEntity result = getGenreEntityById(id);  //this step can be skipped when using stored procedures

        //update values
        result.setName(requestDto.getName());
        result.setDescription(requestDto.getDescription());

        //save to database
        result = genreRepository.save(result);

        //return entity to controller via de DTO
        return genreDTOMapper.mapToDto(result);
    }


    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }


    //private function to check if record exists
    //this function can be skipped when using stored procedures
    private GenreEntity getGenreById(Long id) {
        // First check if record exists in database
        Optional<GenreEntity> genreEntityOptional = genreRepository.findById(id);

        if(genreEntityOptional.isPresent()){
            return genreEntityOptional.get();  //return the found Entity
        } else {
            return null;
        }
    }

    //private function to check if entity exists
    private GenreEntity getGenreEntityById(Long id) {
        GenreEntity genreEntity = genreRepository.findById(id).orElse(null);
        return genreEntity;

        //or inline:
        //return genreRepository.findById(id).orElse(null);
    }
}
