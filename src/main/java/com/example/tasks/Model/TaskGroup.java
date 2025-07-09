package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "task_group")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_group_id")
    private long taskGroupId;

    @Column(name = "task_group_name")
    @NotEmpty
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String taskGroupName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference // Evita loop infinito na serialização JSON
    private Board board;

    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "task_group_tasks")
    private List<Task> tasks = new ArrayList<>();
}
