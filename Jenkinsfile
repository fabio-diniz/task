pipeline {
    stages {
        stage("Build") {
            steps {
                script{
                    shell withMaven: 'mvn clean install'
                }
            }
        }
    }
}