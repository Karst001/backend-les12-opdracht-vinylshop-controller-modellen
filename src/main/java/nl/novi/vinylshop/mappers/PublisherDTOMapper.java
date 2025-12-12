package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherDTOMapper implements DTOMapperInterface<PublisherResponseDTO, PublisherRequestDTO, PublisherEntity> {
    @Override
    public PublisherResponseDTO mapToDto(PublisherEntity model) {
        //instantiate
        PublisherResponseDTO response = new PublisherResponseDTO();

        //set values
        response.setId(model.getId());
        response.setName(model.getName());
        response.setAddress(model.getAddress());
        response.setContactDetails(model.getContactDetails());

        //return single object
        return response;
    }


    @Override
    public List<PublisherResponseDTO> mapToDto(List<PublisherEntity> models) {
        //instantiate
        List<PublisherResponseDTO> response = new ArrayList<PublisherResponseDTO>();

        //add values to List
        for (PublisherEntity model : models) {
            response.add(mapToDto(model));
        }

        //return list of response
        return response;
    }


    @Override
    public PublisherEntity mapToEntity(PublisherRequestDTO genreModel) {
        //instantiate
        PublisherEntity entity = new PublisherEntity();

        //set values
        entity.setName(genreModel.getName());
        entity.setAddress(genreModel.getAddress());
        entity.setContactDetails(genreModel.getContactDetails());

        //return entity
        return entity;
    }
}
