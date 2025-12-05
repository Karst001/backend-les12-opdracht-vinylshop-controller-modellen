package nl.novi.vinylshop.repositories;

import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    //no methods defined because Jpa contains already methods like
    //findAll()
    //findById()
    //save()
    //deleteById()
    //count()
    //existsById()

    //if a custom method is needed like 'findByName' then we need to add like below
    //GenreEntity findByName(String name)
}
