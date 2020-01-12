package ind.leslie.collie.scheduler

import ind.leslie.collie.job.Job
import ind.leslie.collie.job.JobDefinition
import ind.leslie.collie.job.JobFactory
import ind.leslie.collie.job.task.predicate.PredicateInterpreter

class Scheduler private constructor(val job: Job) {

    private var listener: ScheduleTaskListener? = null

    class Builder {

        private var predicateInterpreter: PredicateInterpreter? = null

        private var scheduleTaskListener: ScheduleTaskListener? = null

        fun withPredicateInterpreter(predicateInterpreter: PredicateInterpreter): Builder {
            this.predicateInterpreter = predicateInterpreter
            return this
        }

        fun registerScheduleListener(scheduleTaskListener: ScheduleTaskListener): Builder {
            this.scheduleTaskListener = scheduleTaskListener
            return this
        }

        fun build(jobDefinition: JobDefinition): Scheduler {

            if (this.predicateInterpreter == null) {
                throw NullPointerException("missing a predicate interpreter.")
            }
            val job = JobFactory.create(jobDefinition, this.predicateInterpreter!!)
            val scheduler = Scheduler(job)
            if (this.scheduleTaskListener != null) {
                scheduler.setListener(this.scheduleTaskListener!!)
            }
            return scheduler
        }

    }

    fun run() {
        val sortedTasks = this.job.tasks.sorted()
        for (task in sortedTasks) {
            println("do task $task")
            for (validator in task.validators) {
                val passed = validator.validate()
                if (!passed) {
                    break
                }
            }
            if (this.listener != null){
                this.listener!!.onTask(task)
            }
        }
    }

    private fun setListener(scheduleTaskListener: ScheduleTaskListener) {
        this.listener = scheduleTaskListener
    }


}