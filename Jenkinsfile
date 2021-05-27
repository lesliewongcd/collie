pipeline {
  agent none
  stages {
    stage('job1') {
      parallel {
        stage('job1') {
          steps {
            build(job: 'job1', quietPeriod: 1)
          }
        }

        stage('job2') {
          steps {
            build(job: 'job2', quietPeriod: 1)
          }
        }

      }
    }

    stage('job3') {
      steps {
        build 'job3'
      }
    }

  }
}