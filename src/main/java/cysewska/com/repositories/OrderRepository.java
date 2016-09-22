package cysewska.com.repositories;

import cysewska.com.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ola on 2016-09-10.
 */
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
