pipeline {

    environment {
        registry = "dheerajdac/config_server"
        registryCredential = 'dockerhub'
        dockerImage = ''
     }

    agent any

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                }
            }

            steps {
                sh 'mvn -B clean package'
                sh 'ls'
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
        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        }
    }


}
