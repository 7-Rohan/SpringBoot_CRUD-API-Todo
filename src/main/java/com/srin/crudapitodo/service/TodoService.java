package com.srin.crudapitodo.service;

import com.srin.crudapitodo.exception.TodoCollectionException;
import com.srin.crudapitodo.model.TodoModel;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

public interface TodoService {

    public void createTodo(TodoModel todo) throws ConstraintViolationException, TodoCollectionException;

    public List<TodoModel> getAll();

    public TodoModel getById(String id) throws TodoCollectionException;

    public void updateById(String id, TodoModel todo) throws TodoCollectionException;

    public void deleteById(String id) throws TodoCollectionException;

    public Map<String, Object> getByPage(int page, String todoName) throws TodoCollectionException;

    public void completingTodoById(String id) throws TodoCollectionException;

}
