pipeline {
  agent any
  stages {

    stage("init") {
      steps {
        script {
          gv = load "script.groovy"
        }
      }
    }

    stage("buildImage") {
      steps {
        script {
          gv.buildImage()
        }
      }
    }
    
    stage("pushToECR") {
      steps {
        script {
          gv.pushToECR()
        }
      }
    }

    stage("deployToEC2") {
      steps {
        script {
          gv.deployToEC2()
        }
      }
    }
  }
}