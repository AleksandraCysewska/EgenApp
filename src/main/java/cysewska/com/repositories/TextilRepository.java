package cysewska.com.repositories;

import cysewska.com.models.entities.TextileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ola on 2016-09-10.
 */
@Transactional(readOnly = false)
public interface TextilRepository  extends JpaRepository<TextileEntity,Long> {



    @Modifying
    @Query("Update TextileEntity t SET t.name=:name , t.colors=:color " +
            ", t.textileQuantity=:quantity , t.textileThickness=:thickness WHERE t.id=:id")
    public void updateTextile(@Param("id") Long id,
                            @Param("name") String name,
                            @Param("color") String color,
                            @Param("quantity") Double quantity,
                            @Param("thickness") Double thickness
                            );

    @Modifying
    @Query("Update TextileEntity t SET t.textileQuantity=:quantity  WHERE t.id=:id")
    public void updateTextileQuantity(@Param("id") Long id,
                              @Param("quantity") Double quantity);



}
