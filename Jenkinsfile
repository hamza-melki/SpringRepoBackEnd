pipeline {
  agent any

  tools {
 // Install the Maven version configured as "M3" and add it to the path.
     maven "M2_HOME"
   }
  environment {
    NEXUS_URL = 'http://192.168.43.69:8081'
    NEXUS_REPOSITORY_ID = 'deploymentRepo'
    NEXUS_REPOSITORY_URL = 'http://192.168.43.69:8081/repository/maven-releases'
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
 stage('SonarQube'){
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Rahma123'
            }
        }
      stage('Build and Deploy') {
      steps {
       
          // Build and deploy Maven project
          echo 'Deploy..';
            sh "mvn deploy -DaltDeploymentRepository=${NEXUS_REPOSITORY_ID}::default::${NEXUS_REPOSITORY_URL} -s /usr/share/maven/conf/settings.xml"

        }
      }
      stage('Test Unitaire'){
            steps{
                script{
                    sh 'mvn test'
                }
            }
        }
      stage('Build docker image'){
            steps{
                script{
                    sh 'sudo docker build -t rahmabenghorbel/tpachatproject .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub_pwd', variable: 'dockerhubpwd')]) {
                   sh 'sudo docker login -u rahmabenghorbel -p ${dockerhubpwd}'

}
                   sh 'sudo docker push rahmabenghorbel/tpachatproject'
                }
            }
        }
}
}

