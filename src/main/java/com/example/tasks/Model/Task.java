package com.example.tasks.Model;

import com.example.tasks.Controller.TaskGroupController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long taskId;

    @NotEmpty
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_description")
    private String taskDescription;

    @Enumerated(EnumType.STRING) // salva como texto (ex: "TODO", "IN_PROGRESS", "DONE")
    @Column(name = "task_status")
    private TaskStatus taskStatus;


    @ManyToOne(optional = false)
    @JoinColumn(name = "task_group_id")
    @JsonBackReference // Evita loop infinito na serialização JSON
    private TaskGroup taskGroup;



    public Task(String name, String description, String status) {
    }
}