package nl.novi.vinylshop.repositories;

import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long>{
    //no methods defined because Jpa contains already methods like
    //findAll()
    //findById()
    //save()
    //deleteById()
    //count()
    //existsById()

    //if a custom method is needed like 'findByName' then we need to add like below
    //PublisherEntity findByName(String name);
}
