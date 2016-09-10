package cysewska.com.repositories;

import cysewska.com.entities.UsersEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/*
* Created by cysewskaa on 2016-09-05.
*/


public interface UserRepository extends JpaRepository<UsersEntity,Long> {




}
