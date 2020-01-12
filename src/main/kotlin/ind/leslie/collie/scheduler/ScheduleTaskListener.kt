package ind.leslie.collie.scheduler

import ind.leslie.collie.job.task.Task

interface ScheduleTaskListener {
    fun onTask(task: Task)
}
