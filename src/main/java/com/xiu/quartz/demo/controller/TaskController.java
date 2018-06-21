package com.xiu.quartz.demo.controller;

import com.xiu.quartz.demo.model.JobModel;
import com.xiu.quartz.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/20 14:25
 * @Description 类描述:
 */

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("")
    public Object addTask(@RequestBody JobModel jobModel){
        return taskService.addTask(jobModel);
    }

    @DeleteMapping("")
    public Object delTask(@RequestParam Map<String,String> map){
        return taskService.delTask(map);
    }

    @PutMapping("")
    public Object updateTask(@RequestBody  JobModel jobModel){

        return taskService.updateTask(jobModel);
    }
}
