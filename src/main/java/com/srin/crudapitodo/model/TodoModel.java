package com.srin.crudapitodo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoModel {

    @Id
    private String id;

    @NotNull(message = "todo can't be null")
    private String todo;

    private String description;
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
    private Date completedAt;

}
