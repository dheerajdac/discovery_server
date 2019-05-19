pipeline {
    environment {
        registry = "dheerajdac/discovery_server"
        registryCredential = 'dockerhub'
        artifact = ''
        dockerImage = ''
     }

    agent any

        stages {

            stage('Build') {
                ws('workspace/discovery_server_master'){
                agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v $HOME/.m2:/root/.m2'
                    }
                }

                steps {
                    sh 'mvn -B clean package'
                    sh 'ls target'
                    sh 'pwd'
                }
                }
            }
            stage('Building Image') {
                steps{
                    sh 'pwd'
                    sh 'mvn'
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
