pipeline {
    agent any
     tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
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