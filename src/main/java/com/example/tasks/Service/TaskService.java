package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository, TaskGroupRepository taskGroupRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    // Cria uma tarefa
    public Task createTask(Task task) {
        if (task.getTaskTitle() == null || task.getTaskTitle().length() < 3) {
            throw new IllegalArgumentException("O nome da tarefa deve ter pelo menos 3 caracteres");
        }

        if (task.getTaskStatus() == null) {
            throw new IllegalArgumentException("O status da tarefa não pode ser nulo");
        }

        boolean exists = taskGroupRepository.existsById(task.getTaskGroup().getTaskGroupId());
        if (!exists) {
            throw new IllegalArgumentException("Grupo de tarefas não válido");
        }

        return taskRepository.save(task);
    }

    // Obtém uma tarefa pelo ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada com id: " + id));
    }

    // Obtém todas as tarefas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Obtém todas as tarefas de um grupo
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada com id: " + id));

        if (updatedTask.getTaskTitle() != null && !updatedTask.getTaskTitle().isEmpty()) {
            existingTask.setTaskTitle(updatedTask.getTaskTitle());
        }

        if (updatedTask.getTaskStatus() != null) {
            existingTask.setTaskStatus(updatedTask.getTaskStatus());
        }

        if (updatedTask.getTaskGroup() != null) {
            boolean exists = taskGroupRepository.existsById(updatedTask.getTaskGroup().getTaskGroupId());
            if (!exists) {
                throw new IllegalArgumentException("Grupo de tarefas não é válido");
            }
            existingTask.setTaskGroup(updatedTask.getTaskGroup());
        }

        return taskRepository.save(existingTask);
    }

    // Deleta uma tarefa pelo ID
    public void deteteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("Tarefa não encontrada com id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}
