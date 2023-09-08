package com.orhp.crud.repositories;


import com.orhp.crud.entitites.Course;
import com.orhp.crud.entitites.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByName(String name);

}
