package nl.novi.vinylshop.services;

import nl.novi.vinylshop.entities.PublisherEntity;
import nl.novi.vinylshop.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherEntity> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public PublisherEntity findPublisherById(Long id) {
        PublisherEntity result = getPublisherById(id);

        if (result != null) {
            return publisherRepository.findById(id).orElse(null);
        }
        return null;
    }


    public PublisherEntity createPublisher(PublisherEntity publisher) {
        return publisherRepository.save(publisher);
    }


    public PublisherEntity updatePublisher(Long id, PublisherEntity publisher) {
        PublisherEntity result = (getPublisherById(id));
        if (result != null) {
            // Update fields only, not the id
            result.setName(publisher.getName());
            result.setAddress(publisher.getAddress());
            result.setContactDetails(publisher.getContactDetails());

            //save to database
            return publisherRepository.save(result);
        }
        return null;
    }


    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }


    //private function to check if record exists
    private PublisherEntity getPublisherById(Long id) {
        // First check if record exists in database
        PublisherEntity existing = publisherRepository.findById(id).orElse(null);

        if (existing == null) {
            // record not found
            return null;
        }
        return existing;    //return the found Entity
    }
}
