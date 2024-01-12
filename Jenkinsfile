pipeline {
    agent any

    environment {
        NOTIFICATION_EMAIL = 'abdelkarimsemlali67@gmail.com'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    // Your build steps go here
                    echo 'Building...'
                }
            }
        }
    }

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
}
