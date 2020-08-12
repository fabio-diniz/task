pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                script{
                    checkout scm
                }
            }
        }
        stage("Build") {
            steps {
                script{
                    shell withMaven: 'mvn clean install'
                }
            }
        }
    }
}