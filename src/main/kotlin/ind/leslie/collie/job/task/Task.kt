package ind.leslie.collie.job.task

import ind.leslie.collie.job.Job
import ind.leslie.collie.job.task.predicate.PredicateValidator

class Task(private val order: Int, val metadata: TaskMetadata, val validators: List<PredicateValidator>): Comparable<Task> {

    class Builder {

        private var id: String = ""

        private var name: String = ""

        private var description: String = ""

        private var order = -1

        private val validators: MutableList<PredicateValidator> = mutableListOf()

        private var job: Job? = null

        fun addPredicateValidator(validator: PredicateValidator): Builder {
            this.validators.add(validator)
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
        fun order(order: Int): Builder {
            this.order = order
            return this
        }
        fun build(): Task {
            return Task(
                this.order,
                TaskMetadata(
                    this.id,
                    this.name,
                    this.description
                ), this.validators.toList()
            )
        }
    }

    override fun compareTo(other: Task): Int {
        if (this.order < other.order)
            return 1
        if (this.order > other.order)
            return -1
        return 0
    }

    override fun toString(): String {
        return "[id: ${this.metadata.id}, name: ${this.metadata.name}, order: ${this.order}]"
    }
}