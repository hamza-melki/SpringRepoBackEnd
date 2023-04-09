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
              git branch :'Master',
               url : 'https://github.com/hamza-melki/SpringRepoBackEnd/'



 // To run Maven on a Windows agent, use
 // bat "mvn -Dmaven.test.failure.ignore=true clean package"
 }


 }
     stage('Clean') {
            steps {
                sh 'mvn  clean'
            }
        }
     stage('Compile') {
            steps {
                sh 'mvn  compile'

            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
 stage('Install sonarqube'){
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Rahma123'
            }
        }
}
}

