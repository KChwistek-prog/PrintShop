package com.printshopmanagement.application.repository

import com.printshopmanagement.application.domain.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.sql.Timestamp

@SpringBootTest
class TaskRepositoryTestSuite extends Specification{

    @Autowired
    private TaskDbService taskDbService

    def "should add task to database"(){
        given:
            def bannerComercial = new Task(1L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
        when:
            taskDbService.saveTask(bannerComercial)
        then:
            taskDbService.getTask(1L).isPresent()
            taskDbService.getTask(1L).get().getTaskName() == "Banner Commercial"
    }

    def "should return list of tasks"(){
        given:
            def bannerComercial = new Task(1L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
            def commercial = new Task(2L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
            taskDbService.saveTask(bannerComercial)
            taskDbService.saveTask(commercial)
        when:
            var result = taskDbService.getTasks()
        then:
            result.size() == 2
    }

    def "should return task from database"(){
        given:
            def bannerCommercial = new Task(1L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
            taskDbService.saveTask(bannerCommercial)
        when:
            var result = taskDbService.getTask(1L)
        then:
            result.isPresent()
            result.orElseThrow().getTaskName() == "Banner Commercial"
    }

    def "should remove task from database"(){
        given:
            def bannerComercial = new Task(1L, "Banner Commercial", "banner", "In progress", "n/a", new Timestamp(20200502), new Timestamp(20200505))
            taskDbService.saveTask(bannerComercial)
        when:
            taskDbService.deleteTask(1L)
        then:
            taskDbService.getTask(1L).isEmpty()
    }
}
