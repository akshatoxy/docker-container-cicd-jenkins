pipeline {
  agent any
  stages {
    stage("buildImage") {
      steps {
        echo "Building Docker image"
      }
    }
    stage("pushImage") {
      steps {
        echo "Pushing image to ECR"
      }
    }
    stage("deploy") {
      steps {
        echo "Deploying to EC2"
      }
    }
  }
}