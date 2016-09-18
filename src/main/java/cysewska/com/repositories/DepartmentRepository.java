package cysewska.com.repositories;

import cysewska.com.models.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ola on 2016-09-18.
 */
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
}
