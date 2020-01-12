package ind.leslie.collie.app

import ind.leslie.collie.job.JobDefinition
import ind.leslie.collie.job.task.Task
import ind.leslie.collie.job.task.TaskDefinition
import ind.leslie.collie.job.task.predicate.PredicateDefinition
import ind.leslie.collie.job.task.predicate.PredicateInterpreter
import ind.leslie.collie.job.task.predicate.PredicateMetadata
import ind.leslie.collie.job.task.predicate.PredicateValidator
import ind.leslie.collie.scheduler.ScheduleTaskListener
import ind.leslie.collie.scheduler.Scheduler
import kotlin.random.Random

class App


fun main() {

    val jobDefinition: JobDefinition = mockJobDefinition()

    Scheduler.Builder()
        .withPredicateInterpreter(object : PredicateInterpreter {
            override fun interpret(metadata: PredicateMetadata): PredicateValidator {
                return SamplePredicateValidator(metadata)
            }
        })
        .registerScheduleListener(object : ScheduleTaskListener {
            override fun onTask(task: Task) {
                println("execute task $task")
            }
        })
        .build(jobDefinition)
        .run()
}

fun mockJobDefinition(): JobDefinition {
    val jobDefinition = JobDefinition()
    jobDefinition.id = "j1"
    jobDefinition.name = "os"
    jobDefinition.description = "mock a job"

    val random = Random(1000)
    val list = mutableListOf<TaskDefinition>()
    for (i in 1 .. 3){
       list.add(mockTaskDefinition(random.nextInt(1000)))
    }
    jobDefinition.tasks = list
    return jobDefinition
}

fun mockTaskDefinition(index: Int): TaskDefinition {
    val taskDefinition = TaskDefinition()
    taskDefinition.id = "t$index"
    taskDefinition.name = "name_$index"
    taskDefinition.description = "mock a task with index $index"
    taskDefinition.order = index
    taskDefinition.predicates = listOf(
        PredicateDefinition("version > v1"),
        PredicateDefinition("file exists"))
    return taskDefinition
}
