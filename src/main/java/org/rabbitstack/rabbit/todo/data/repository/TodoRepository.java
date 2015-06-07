package org.rabbitstack.rabbit.todo.data.repository;

import org.rabbitstack.rabbit.todo.data.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The mongo repository for <code>Todo</code> documents. It gives us
 * the basic CRUD operations out of the box.
 *
 * @author Nedim Sabic
 */
public interface TodoRepository extends MongoRepository<Todo, String> {

}
