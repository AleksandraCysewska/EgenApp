package cysewska.com.repositories;

import cysewska.com.models.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ola on 2016-09-18.
 */
@Transactional
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {

    @Modifying
    @Query("Update TextileEntity t SET t.name=:name , t.colors=:color " +
            ", t.textileQuantity=:quantity , t.textileThickness=:thickness WHERE t.id=:id")
    public void updateDepartment(@Param("id") Long id,
                              @Param("name") String name,
                              @Param("color") String color,
                              @Param("quantity") Double quantity,
                              @Param("thickness") Double thickness
    );
}
