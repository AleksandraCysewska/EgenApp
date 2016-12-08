package cysewska.com.repositories;

import cysewska.com.models.entities.ClothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ola on 2016-09-21.
 */
@Transactional(readOnly = true)
public interface ClothRepository  extends JpaRepository<ClothEntity,Long> {
}
