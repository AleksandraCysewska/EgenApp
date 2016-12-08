package cysewska.com.repositories;

import cysewska.com.models.entities.UsersEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/*
* Created by cysewskaa on 2016-09-05.
*/

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UsersEntity,Long> {




}
