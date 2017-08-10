package edu.group.potluck.repositories;

import edu.group.potluck.Dishes;
import org.springframework.data.repository.CrudRepository;

public interface PotluckRepo extends CrudRepository<Dishes,Long>
{

    Iterable<Dishes> findAllByNameContains(String partialName);
    Iterable<Dishes> findAllByDishContains(String partialDish);

}
