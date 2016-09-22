package cysewska.com.repositories;

import cysewska.com.models.entities.OrderClothEntity;
import cysewska.com.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ola on 2016-09-22.
 */
public interface OrderClothRepository  extends JpaRepository<OrderClothEntity,Long> {
}
