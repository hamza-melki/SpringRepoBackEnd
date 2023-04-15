pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M2_HOME" and add it to the PATH
        maven 'M2_HOME'

}

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling..'
                // Get some code from a GitHub repository
                git branch: 'HamzaB_branch',
                url: 'https://github.com/hamza-melki/SpringRepoBackEnd.git'
            }
        }

                stage('Build') {
            steps {
                 // Install and configure Maven

                sh ' cd tpAchatProject/ && mvn clean install' // Executes Maven clean build
            }
        }
        stage('Test'){
            steps{
                sh 'cd tpAchatProject/ && mvn test'
            }
            post{
                always{
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
              tools {
        jdk "JAVA_HOME" // the name you have given the JDK installation in Global Tool Configuration
    }
            steps {
                withSonarQubeEnv('SQ1') { // Replace 'SonarQube Server' with your SonarQube server configuration in Jenkins
                    sh 'cd tpAchatProject/ && mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=hamza11' // Executes SonarQube analysis using Maven SonarQube plugin
                }
                  }
        }
          stage('Deploy to Nexus') {
            steps {
                // Install and configure Maven
                sh 'cd tpAchatProject/ && mvn deploy -Dmaven.deploy.skip=true' // Executes Maven deploy with skip option to skip actual deployment



   nexusArtifactUploader(
        nexusVersion: 'nexus3',
        protocol: 'http',
        nexusUrl: '192.168.1.21:8081',
        groupId: 'com.esprit.examen',
        version: "${env.BUILD_ID}-${env.BUILD_TIMESTAMP}",
        repository: 'Maven-Central-Repo',
        credentialsId: 'Nexus_CRED',
        artifacts: [
            [artifactId: 'tpAchatProject',
             classifier: '',
             file: 'tpAchatProject/target/tpAchatProject-' + '1.0' + '.jar',
             type: 'jar']
             ]

                            )
            }
        }
        stage('Build docker image')
        {
            steps{
                script{
                    sh 'cd tpAchatProject/ && sudo docker build -t hamza03/tpachatproject-1.0 .'
                }
            }
        }
        stage('push to hub')
        {
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')])
                    {
                        sh 'sudo docker login -u hamza03 -p ${dockerhubpwd}'
                    }
                    sh 'sudo docker push hamza03/tpachatproject-1.0'

                }
            }
        }
         stage('Build and Run the projects docker container') {
            steps {
                echo 'Building and Running Docker Container..'
                // Change to the directory where your docker-compose.yml file is located
                dir('tpAchatProject/') {
                    // Build and run Docker containers using Docker Compose
                    sh 'docker-compose up -d'
                }
            }
        }
         stage('Email notification')
                {
                    steps{
        mail bcc: '', body: '''Dear Team,

        This is an automated email to provide you with the latest execution report from the Jenkins pipeline for the Springboot app project. The pipeline was executed successfully with the following details:

        Build ID: ${BUILD_ID}
        Execution Time: ${BUILD_TIMESTAMP, format="yyyy-MM-dd HH:mm:ss"}
        Build Status: ${BUILD_STATUS}
        Commit Hash: ${GIT_COMMIT}
        Branch: ${GIT_BRANCH}
        Environment: ${ENVIRONMENT}

        Pipeline Stages:

        Checkout: The repository was successfully checked out.
        Build: The code was built successfully.
        Test: Automated tests were executed and passed.
        SonarQube: Static code analysis was performed using SonarQube.
        Nexus: Artifacts were uploaded to Nexus repository.
        Docker: Docker image was built and pushed to Docker registry.
        Build Artifacts:

        [Artifact 1]: [Artifact 1 Details]
        [Artifact 2]: [Artifact 2 Details]
        ...
        SonarQube Analysis:

        Total Issues: ${SONAR_TOTAL_ISSUES}
        Code Smells: ${SONAR_CODE_SMELLS}
        Bugs: ${SONAR_BUGS}
        Vulnerabilities: ${SONAR_VULNERABILITIES}
        Coverage: ${SONAR_COVERAGE}
        Duplications: ${SONAR_DUPLICATIONS}
        Please review the pipeline execution report and take necessary actions, if any. If you encounter any issues or need further assistance, please do not hesitate to reach out to us.

        Thank you for your attention to this report.

        Best regards,''', cc: '', from: '', replyTo: '', subject: 'Jenkins Pipeline Execution Report - Springboot app', to: 'hamza.belatra@esprit.tn'        }


            }

    }

}
