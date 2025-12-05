package nl.novi.vinylshop.services;

import nl.novi.vinylshop.dtos.genre.GenreRequestDTO;
import nl.novi.vinylshop.dtos.genre.GenreResponseDTO;
import nl.novi.vinylshop.dtos.publisher.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.publisher.PublisherResponseDTO;
import nl.novi.vinylshop.entities.BaseEntity;
import nl.novi.vinylshop.entities.GenreEntity;
import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.mappers.DTOMapperInterface;
import nl.novi.vinylshop.mappers.PublisherDTOMapper;
import nl.novi.vinylshop.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    //dependency injections
    private final PublisherRepository publisherRepository;
    private final PublisherDTOMapper publisherDTOMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherDTOMapper publisherDTOMapper) {
        //instantiate
        this.publisherRepository = publisherRepository;
        this.publisherDTOMapper = publisherDTOMapper;
    }


    public List<PublisherResponseDTO> findAllPublishers() {
        //return the GenreResponseDTO via the DTO mapper to the controller
        return publisherDTOMapper.mapToDto(publisherRepository.findAll());
    }


    public PublisherResponseDTO findPublisherById(Long id) {
        //check if records exists
        PublisherEntity result = getPublisherById(id);   //this step can be skipped when using stored procedures

        if (result != null) {
            DTOMapperInterface<Object, Object, BaseEntity> publisherDtoMapper;
            return publisherDTOMapper.mapToDto(result);
        }
        return null;
    }



    public PublisherResponseDTO createPublisher(PublisherRequestDTO publisherDTO) {
        //map the genreDTO to the genreEntity
        PublisherEntity result = publisherDTOMapper.mapToEntity(publisherDTO);

        //call the repo and save the result
        result = publisherRepository.save(result);

        //return the publisherEntity via the DTO mapper to the controller
        return publisherDTOMapper.mapToDto(result);
    }


    public PublisherResponseDTO updatePublisher(Long id, PublisherRequestDTO requestDto) {
        //check if records exists
        PublisherEntity result = getPublisherEntityById(id);  //this step can be skipped when using stored procedures

        //update values
        result.setName(requestDto.getName());
        result.setAddress(requestDto.getAddress());
        result.setContactDetails(requestDto.getContactDetails());

        //save to database
        result = publisherRepository.save(result);

        //return entity to controller via de DTO
        return publisherDTOMapper.mapToDto(result);
    }


    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }


    //private function to check if record exists
    //this function can be skipped when using stored procedures
    private PublisherEntity getPublisherById(Long id) {
        // First check if record exists in database
        PublisherEntity existing = publisherRepository.findById(id).orElse(null);

        if (existing == null) {
            // record not found
            return null;
        }
        return existing;    //return the found Entity
    }


    //private function to check if entity exists
    private PublisherEntity getPublisherEntityById(Long id) {
        PublisherEntity publisherEntity = publisherRepository.findById(id).orElse(null);
        return publisherEntity;

        //or inline:
        //return publisherRepository.findById(id).orElse(null);
    }
}
