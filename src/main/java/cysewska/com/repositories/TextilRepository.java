package cysewska.com.repositories;

import cysewska.com.models.entities.TextileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ola on 2016-09-10.
 */
public interface TextilRepository  extends JpaRepository<TextileEntity,Long> {
}
