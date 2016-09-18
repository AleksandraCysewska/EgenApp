package cysewska.com.repositories;

import cysewska.com.models.entities.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cysewskaa on 2016-09-05.
 */
public interface BranchRepository extends JpaRepository<BranchEntity,Long> {
}
