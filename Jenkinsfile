pipeline {
    environment {
        registry = "dheerajdac/discovery_server"
        registryCredential = 'dockerhub'
        dockerImage = ''
     }

    agent any

        stages {

            stage('Build') {
                agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /var/run/docker.sock:/var/run/docker.sock'
                    }
                }

                steps {
                    sh 'mvn -B clean package'
                    sh 'ls target'
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
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
