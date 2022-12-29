pipelineJob("dev-deployment/$jobName") {
  definition {
    cps {
      script('''
        pipeline {
          agent any
            stages {
              stage('Checkout'){
                steps {
                   git 'https://Guruprasad4126@bitbucket.org/arunmadan1991/qdp-jenkins.git'
                }
              }
              stage('Build $CodeBuild project') {
                steps {
                   sh "mvn clean install"
                }
              }
              stage('Sonarqube analysis') {
                steps{
                  sh "echo code analysis stage"
                }
              }
              stage('Quality gates') {
                steps{
                  sh "echo quality gates analysis"
                }
              }
              stage('Uploading Artifacts') {
                steps{
                  sh "echo artifacts uploaded"
                }
              }
              stage('Build docker image') {
                steps{
                  sh "echo docker image built"
                }
              }
              stage('push docker image to dockerhub') {
                steps{
                  sh "echo docker image pushed"
                }
              }
              stage('Notify Sentry of deployment') {
                steps{
                  sh "echo Notification stage"
                }
              }
              stage('Deploy docker image') {
                steps{
                  sh "echo Deployment successfull"
                }
              }
            }
        }
     
      '''.stripIndent())
      sandbox()     
    }
  }
}
