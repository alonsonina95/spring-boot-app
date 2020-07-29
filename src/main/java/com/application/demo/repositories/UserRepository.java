package com.application.demo.repositories;

import com.application.demo.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Contacts, Integer> {
}
