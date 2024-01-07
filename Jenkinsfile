pipeline {
    agent any

    environment {
        NOTIFICATION_EMAIL = 'abdelkarimsemlali67@gmail.com'
    }

    stages {
        stage('Load Scripts') {
            steps {
                script {
                    // Load notificationManager.groovy
                    def notificationManagerScript = evaluate(new File('notificationManager.groovy'))

                    // Load email.html.groovy
                    def emailHtmlScript = evaluate(new File('email.html.groovy'))

                    // Set as environment variables
                    env.NOTIFICATION_MANAGER_SCRIPT = notificationManagerScript
                    env.EMAIL_HTML_SCRIPT = emailHtmlScript
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