pipeline {
    stages {
        stage("Build") {
            script{
                shell withMaven: 'mvn clean install'
            }
        }
    }
}