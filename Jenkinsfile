properties([parameters([gitParameter(branch: '', branchFilter: '.*', defaultValue: 'test/ashok', description: 'select branch', name: 'branch', quickFilterEnabled: false, selectedValue: 'NONE', sortMode: 'NONE', tagFilter: '*', type: 'PT_BRANCH')])])
pipeline {
  agent any
  tools {
    maven "Maven3"
  }
  environment {
    registry = "ashok8080/vmauth"
    registryCredential = 'dockerhub_2'
    dockerImage = ''
  }
  stages {
    stage("Generating .war file for vmauth") {
      steps {
        bat 'mvn clean install'
      }
    }
    stage('Building our image') {
      steps {
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy our image') {
      steps {
        script {
          docker.withRegistry('', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
  }
  post {
    success {
      //echo "${env.BUILD_URL} has result success"
      echo "test info ${BUILD_TIMESTAMP}"
      emailext(
        recipientProviders: [
          [$class: 'DevelopersRecipientProvider'],
          [$class: 'RequesterRecipientProvider']
        ],
        subject: "Build:${currentBuild.currentResult} for this job:${env.JOB_NAME}",
        body: "Build Status : ${currentBuild.currentResult}\nJob : ${env.JOB_NAME}\nBuild Num: ${env.BUILD_NUMBER}.\nDate Of Build:${BUILD_TIMESTAMP}\nView the log at:\n ${env.BUILD_URL}\n"
        //subject:"${env.JOB_NAME}"
      );
    }
    unstable {
      echo 'I am unstable :/'
    }
    failure {
      echo "failed script"
      emailext(
        recipientProviders: [
          [$class: 'DevelopersRecipientProvider'],
          [$class: 'RequesterRecipientProvider']
        ],
        subject: "Build:${currentBuild.currentResult} for this job:${env.JOB_NAME}",
        body: "Build Status : ${currentBuild.currentResult}\njob : ${env.JOB_NAME}\nBuild Num: ${env.BUILD_NUMBER}.\nDate Of Build:${BUILD_TIMESTAMP}\n View the log at:\n ${env.BUILD_URL}\n"
        //subject:"${env.JOB_NAME}"
      );
    }
  }
}
