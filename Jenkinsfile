/*
* `buildPlugin` step provided by: https://github.com/jenkins-infra/pipeline-library
*/
pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2_HOME"
    }

    stages {
        stage('Testing  version') {
         steps {
                echo 'mvn version..';
                sh """mvn --version"""

         }

        }
        stage('Checkout GIT') {
            steps {
                echo 'Pulling..';
                // Get some code from a GitHub repository
                git branch :'master',
                url : 'https://github.com/hamza-melki/SpringRepoBackEnd.git'

                // Run Maven on a Unix agent.


                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }


        }


    }
}
