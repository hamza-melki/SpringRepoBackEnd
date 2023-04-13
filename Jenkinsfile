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
      
      
     stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
      stage('Build') {
            steps {
                // Checkout your source code from version control system
                // ...
               echo 'Build..';
                // Invoke Maven build
               sh 'mvn clean install'
                
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
      stage('Build and Deploy') {
      steps {
       
          // Build and deploy Maven project
          
                            sh "mvn deploy -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -DskipTests=true -DaltDeploymentRepository=my-nexus-repo::default::http://192.168.43.69:8081/repository/maven-snapshots/ -Dusername=${admin} -Dpassword=${Rahma123}"

        }
      }
}
}

