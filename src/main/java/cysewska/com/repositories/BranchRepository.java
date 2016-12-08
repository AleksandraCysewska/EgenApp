package cysewska.com.repositories;

import cysewska.com.models.entities.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cysewskaa on 2016-09-05.
 */
@Transactional(readOnly = true)
public interface BranchRepository extends JpaRepository<BranchEntity,Long> {
}
