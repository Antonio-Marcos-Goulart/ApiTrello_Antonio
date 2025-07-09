package com.example.tasks.Controller;

import com.example.tasks.Service.TaskGroupService;
import com.example.tasks.Model.TaskGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task_group")
public class TaskGroupController {

    private TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService){
        this.taskGroupService = taskGroupService;
    }

    @PostMapping
    public TaskGroup createTaskGroup(@RequestBody TaskGroup taskGroup){
        return taskGroupService.createTask(taskGroup);
    }

    @GetMapping
    public List<TaskGroup> getAllTaskGroups(){
        return taskGroupService.getAllTaskGroups();
    }

    @GetMapping("/{id}") // Obtém o grupo de tarefas pelo ID
    public TaskGroup getTaskGroupById(@PathVariable Long id){
        return taskGroupService.getTaskServiceById(id);
    }


    @PutMapping("/{id}") // Atualiza o grupo de tarefas com o ID especificado
    public TaskGroup updateTaskGroup(@PathVariable Long id, @RequestBody TaskGroup updatedTaskGroup){
        return taskGroupService.updateTaskGroup(id, updatedTaskGroup);
    }

    @DeleteMapping("/{id}") // Deleta o grupo de tarefas com o ID especificado
    public void deleteTaskGroup(@PathVariable Long id){
        taskGroupService.deleteTaskGroup(id);
    }
}
