package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, BoardRepository boardRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }

    // Criar um grupo de tarefas
    public TaskGroup createTask(TaskGroup taskGroup){
        if (taskGroup.getTaskGroupName() == null || taskGroup.getTaskGroupName().length() < 3) {
            throw new IllegalArgumentException("O nome do grupo de tarefas deve ter pelo menos 3 caracteres");
        }
        if (taskGroup.getBoard() == null) {
            throw new IllegalArgumentException("Board não pode ser nulo");
        }
        Long boardId = taskGroup.getBoard().getBoardId();
        System.out.println("Board ID recebido: " + boardId); // log para debug - imprime o valor do boardId no console em tempo de execução
        if (boardId == null) {
            throw new IllegalArgumentException("Board Id não pode ser nulo");
        }
        boolean exists = boardRepository.existsById(boardId);
        System.out.println("Board existe no repository? " + exists); // log para debug - imprime se o board existe no repository
        if (!exists) {
            throw new IllegalArgumentException("Board não é válido");
        }
        return taskGroupRepository.save(taskGroup);
    }

    // Obter grupo de tarefas pelo ID
    public TaskGroup getTaskServiceById(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo de tarefas não encontrado com id: " + id));
    }

    // Obter todos os grupos de tarefas do banco de dados
    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    // Atualizar o grupo de tarefas
    public TaskGroup updateTaskGroup(Long id, TaskGroup updatedTaskGroup) {
        TaskGroup existingTaskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo de tarefas não encontrado com id: " + id));

        if (updatedTaskGroup.getTaskGroupName() != null && !updatedTaskGroup.getTaskGroupName().isEmpty()) {
            existingTaskGroup.setTaskGroupName(updatedTaskGroup.getTaskGroupName());
        }

        if (updatedTaskGroup.getBoard() != null) {
            boolean exists = boardRepository.existsById(updatedTaskGroup.getBoard().getBoardId());
            if (!exists) {
                throw new IllegalArgumentException("Board não é válido");
            }
            existingTaskGroup.setBoard(updatedTaskGroup.getBoard());
        }
        return taskGroupRepository.save(existingTaskGroup);
    }

    // Deletar o grupo de tarefas e ver se ele existe
    public void deleteTaskGroup(Long taskGroupId) {
        if (!taskGroupRepository.existsById(taskGroupId)) {
            throw new IllegalArgumentException("Grupo de tarefas não encontrado com id: " + taskGroupId);
        }
        taskGroupRepository.deleteById(taskGroupId);
    }

}
