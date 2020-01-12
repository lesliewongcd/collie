package ind.leslie.collie.job

import ind.leslie.collie.job.task.Task

class Job(val metadata: JobMetadata, val tasks: List<Task>) {

    class Builder {

        private var id: String = ""

        private var name: String = ""

        private var description: String = ""

        private val tasks: MutableList<Task> = mutableListOf()

        fun addTask(task: Task): Builder {
            this.tasks.add(task)
            return this
        }

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun build(): Job {
            return Job(
                JobMetadata(
                    this.id,
                    this.name,
                    this.description
                ), this.tasks.toList()
            )
        }
    }

}