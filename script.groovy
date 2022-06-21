def buildImage() {
    echo "Building the docker image..."
    sh "docker build -t 639504421228.dkr.ecr.us-east-1.amazonaws.com/docker-container-cicd-jenkins:latest ."
}

def pushToECR() {
    withCredentials([usernamePassword(credentialsId: 'aws-ecr-creds', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "echo $PASS | docker login -u $USER --password-stdin 639504421228.dkr.ecr.us-east-1.amazonaws.com"
        sh "docker push 639504421228.dkr.ecr.us-east-1.amazonaws.com/docker-container-cicd-jenkins:latest"
    }
}

def deployToEC2() {
    def ec2Instance = "ec2-user@54.159.173.159"

    sshagent(['web-server-key']) {
        sh "scp -o StrictHostKeyChecking=no server-cmds.sh ${ec2Instance}:/home/ec2-user"
        sh "scp -o StrictHostKeyChecking=no docker-compose.yaml ${ec2Instance}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} bash ./server-cmds.sh"
    }
}

return this