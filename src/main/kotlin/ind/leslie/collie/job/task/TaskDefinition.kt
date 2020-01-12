package ind.leslie.collie.job.task

import ind.leslie.collie.job.task.predicate.PredicateDefinition

class TaskDefinition {

    var id = ""

    var name = ""

    var order = -1

    var description = ""

    var predicates: List<PredicateDefinition> = emptyList()
}
