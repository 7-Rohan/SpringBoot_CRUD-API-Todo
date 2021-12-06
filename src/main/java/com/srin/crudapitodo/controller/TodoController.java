package com.srin.crudapitodo.controller;

import com.srin.crudapitodo.exception.TodoCollectionException;
import com.srin.crudapitodo.model.TodoModel;
import com.srin.crudapitodo.repository.TodoRepository;
import com.srin.crudapitodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAll() {

        List<TodoModel> todos = todoService.getAll();
        return new ResponseEntity<List<TodoModel>>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoModel todo) {

        try {
            todoService.createTodo(todo);
            return new ResponseEntity<TodoModel>(todo, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<TodoModel>(todoService.getById(id), HttpStatus.OK);
        }catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoModel todo) {
        try {
            todoService.updateById(id, todo);
            return new ResponseEntity<>("Update todo with id " +id, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {

        try {
            todoService.deleteById(id);
            return new ResponseEntity<>("Successfully deleted by id " + id, HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/todos1")
    public ResponseEntity<?> getByPage(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "todoName", defaultValue = "") String todoName) {

        try {
            Map<String, Object> todos = todoService.getByPage(page, todoName);
            return new ResponseEntity<Map<String, Object>>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/todos/completed/{id}")
    public ResponseEntity<?> completingTodo(@PathVariable("id") String id) {
        try {
            todoService.completingTodoById(id);
            return new ResponseEntity<>("Todo with id " +id + " completed", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }







}
