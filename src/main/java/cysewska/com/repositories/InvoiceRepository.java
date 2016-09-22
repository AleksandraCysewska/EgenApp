package cysewska.com.repositories;

import cysewska.com.models.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ola on 2016-09-10.
 */
@Transactional(readOnly = true)
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {
}
