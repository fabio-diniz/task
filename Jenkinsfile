pipeline {
    agent any
     tools { 
        maven 'Maven 3.6.3' 
    }
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