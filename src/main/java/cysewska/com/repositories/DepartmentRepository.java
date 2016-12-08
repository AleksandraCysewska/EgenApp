package cysewska.com.repositories;

import cysewska.com.models.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ola on 2016-09-18.
 */
@Transactional(readOnly = true)
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
}
