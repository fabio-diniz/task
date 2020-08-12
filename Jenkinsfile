pipeline {
    agent any
     tools { 
        maven 'Maven 3.6.3' 
    }
    stages {
        stage("Build") {
            steps {
                script{
                    sh 'mvn clean install'
                }
            }
            post {
				success {
					archiveArtifacts artifacts:'target/*.jar', allowEmptyArchive: true
				}
			}
        }
    }
}