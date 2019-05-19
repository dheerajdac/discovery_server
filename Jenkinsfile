pipeline {
    environment {
        registry = "dheerajdac/discovery_server"
        registryLatest = "dheerajdac/discovery_server:latest"
        registryCredential = 'dockerhub'
        dockerImage = ''
     }

    agent {
        docker {
            image 'dheerajdac/ubuntu:9'
            args '-v $HOME/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Building Image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }

        stage('Deploy Image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy qa01'){
            steps{
                script {
                    dockerImage = docker.tag($registry:$BUILD_NUMBER, registryLatest){
                        docker.withRegistry( '', registryCredential ) {
                            dockerImage.push()
                        }
                    }
                }
            }
        }

        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
    }
}
