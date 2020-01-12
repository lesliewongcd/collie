package ind.leslie.collie.app

import ind.leslie.collie.job.task.predicate.PredicateMetadata
import ind.leslie.collie.job.task.predicate.PredicateValidator

class SamplePredicateValidator(val metadata: PredicateMetadata) : PredicateValidator {
    override fun validate(): Boolean {
        println("validate expression [${metadata.predicateDefinition.expression}]")
        return true
    }

}
