package cysewska.com.repositories;

import cysewska.com.models.entities.ClothEntity;
import cysewska.com.models.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cysewskaa on 2016-09-07.
 */
@Transactional(readOnly = true)
public interface ModelRepository extends JpaRepository<ModelEntity,Long> {
}
