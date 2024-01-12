pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('Load and Execute Groovy Script') {
            steps {
                script {
                    def workflowLibsManager = load "notificationManager.groovy"
                    workflowLibsManager.notifyEmail('SUCCESSFUL', 'abdelkarimsemlali67@gmail.com')
                }
            }
        }
    }
/*
    post {
        success {
            script {
                def workflowLibsManager = load "notificationManager.groovy"
                workflowLibsManager.sendNotification(currentBuild.result ?: 'UNKNOWN', [env.NOTIFICATION_EMAIL])
            }
        }

        failure {
            script {
                def workflowLibsManager = load "notificationManager.groovy"
                workflowLibsManager.sendNotification(currentBuild.result ?: 'UNKNOWN', [env.NOTIFICATION_EMAIL])
            }
        }
    }
*/
}
