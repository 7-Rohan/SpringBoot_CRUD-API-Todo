package com.srin.crudapitodo.repository;

import com.srin.crudapitodo.model.TodoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<TodoModel, String> {

    @Query("{'todo': ?0}")
    Optional<TodoModel> findByTodo(String todo);

}
