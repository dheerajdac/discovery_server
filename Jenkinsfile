pipeline {
    environment {
        registry = "dheerajdac/discovery_server"
        registryCredential = 'dockerhub'
        artifact = ''
        dockerImage = ''
     }

    agent {
                        docker {
                            image 'dheerajdac/ubuntu:8'
                            args '-v $HOME/.m2:/root/.m2'
                        }
                    }

        stages {

            stage('Build') {


                steps {
                    sh 'mvn -B clean package'
                    sh 'ls target'
                    sh 'pwd'
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
