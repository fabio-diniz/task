pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                script{
                    withMaven(maven: 'maven-3') {
                        sh 'mvn clean install'
                    }
                }
            }
        }
    }
}