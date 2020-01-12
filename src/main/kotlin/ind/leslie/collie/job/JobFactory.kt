package ind.leslie.collie.job

import ind.leslie.collie.job.task.Task
import ind.leslie.collie.job.task.predicate.PredicateInterpreter
import ind.leslie.collie.job.task.predicate.PredicateMetadata

object JobFactory {

    fun create(jobDefinition: JobDefinition, predicateInterpreter: PredicateInterpreter): Job {
        val jobBuilder = Job.Builder()
            .id(jobDefinition.id)
            .name(jobDefinition.name)
            .description(jobDefinition.description)

        for (taskDefinition in jobDefinition.tasks) {
            val taskBuilder = Task.Builder()
                .id(taskDefinition.id)
                .name(taskDefinition.name)
                .description(taskDefinition.description)
                .order(taskDefinition.order)
            for (predicateDefinition in taskDefinition.predicates) {
                taskBuilder.addPredicateValidator(
                    predicateInterpreter
                        .interpret(PredicateMetadata(jobDefinition.name, taskDefinition.name, predicateDefinition))
                )
            }
            jobBuilder.addTask(taskBuilder.build())
        }
        return jobBuilder.build()
    }

}