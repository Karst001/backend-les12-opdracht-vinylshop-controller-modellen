package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreDTOMapper implements DTOMapperInterface<GenreResponseDTO, GenreRequestDTO, GenreEntity> {
    @Override
    public GenreResponseDTO mapToDto(GenreEntity model) {
        //instantiate
        GenreResponseDTO response = new GenreResponseDTO();

        //set values
        response.setId(model.getId());
        response.setName(model.getName());
        response.setDescription(model.getDescription());

        //return single object of model
        return response;
    }


    @Override
    public List<GenreResponseDTO> mapToDto(List<GenreEntity> models) {
        //instantiate
        List<GenreResponseDTO> response = new ArrayList<GenreResponseDTO>();

        //add values to List
        for (GenreEntity model : models) {
            response.add(mapToDto(model));
        }

        //return List of response
        return response;
    }


    @Override
    public GenreEntity mapToEntity(GenreRequestDTO genreModel) {
        //instantiate
        GenreEntity entity = new GenreEntity();

        //set values
        entity.setName(genreModel.getName());
        entity.setDescription(genreModel.getDescription());

        //return entity
        return entity;
    }
}