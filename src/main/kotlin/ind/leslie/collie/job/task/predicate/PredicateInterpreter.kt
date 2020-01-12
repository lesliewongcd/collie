package ind.leslie.collie.job.task.predicate

interface PredicateInterpreter {

    fun interpret(metadata: PredicateMetadata): PredicateValidator

}