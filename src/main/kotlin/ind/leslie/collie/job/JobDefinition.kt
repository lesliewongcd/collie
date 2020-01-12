package ind.leslie.collie.job

import ind.leslie.collie.job.task.TaskDefinition

class JobDefinition {

    var id = ""

    var name = ""

    var description = ""

    var tasks:List<TaskDefinition> = emptyList()

}
