package com.printshopmanagement.application.mapper

import com.printshopmanagement.application.domain.Task
import com.printshopmanagement.application.domain.TaskDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.sql.Timestamp

@SpringBootTest
class TaskMapperTestSuite extends Specification{

    @Autowired
    private TaskMapper taskMapper

    def bannerCommercial = new Task()
    def bannerCommercialDto = new TaskDto()

    def setup(){
        bannerCommercial = new Task(1L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
        bannerCommercialDto = new TaskDto(1L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
    }

    def "should map task to  task dto"(){
        when:
            var result = taskMapper.mapToTaskDto(bannerCommercial)
        then:
            result.getTaskName() == bannerCommercial.getTaskName()
    }

    def "shuold map task dto to task"(){
        when:
        var result = taskMapper.mapToTask(bannerCommercialDto)
        then:
        result.getTaskName() == bannerCommercial.getTaskName()
    }

    def "should map task list to task dto list"(){
        given:
            List<Task>taskList = new ArrayList<>()
            taskList.add(bannerCommercial)
        when:
            var result = taskMapper.mapToTaskDtoList(taskList)
        then:
            result.get(0).getTaskName() == bannerCommercial.getTaskName()
    }
}
