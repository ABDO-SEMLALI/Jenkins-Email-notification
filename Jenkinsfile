pipeline {
    agent any

    environment {
        NOTIFICATION_MANAGER_SCRIPT = load 'notificationManager.groovy', 'UTF-8'
        EMAIL_HTML_SCRIPT = load 'email.html.groovy', 'UTF-8'
        NOTIFICATION_EMAIL = 'abdelkarimsemlali67@gmail.com'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Your build steps go here
                    echo 'Building...'
                }
            }
        }

        stage('Send Email Notification') {
            steps {
                script {
                    def buildStatus = currentBuild.result ?: 'UNKNOWN'
                    def subject = "Build Status: ${buildStatus}"
                    def body = EMAIL_HTML_SCRIPT.generateEmailBody(buildStatus)
                    
                    NOTIFICATION_MANAGER_SCRIPT.sendNotification(NOTIFICATION_EMAIL, subject, body)
                }
            }
        }
    }
}
