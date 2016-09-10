package cysewska.com.repositories;

import cysewska.com.entities.BranchEntity;
import cysewska.com.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cysewskaa on 2016-09-07.
 */
public interface ClothRepository  extends JpaRepository<ModelEntity,Long> {
}
