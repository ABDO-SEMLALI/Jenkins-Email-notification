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
                    def notificationManagerScript = readFile('notificationManager.groovy')

                    // Load email.html.groovy
                    def emailHtmlScript = readFile('email.html.groovy')

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
                    def emailBody = env.EMAIL_HTML_SCRIPT.generateEmailBody(buildStatus)

                    env.NOTIFICATION_MANAGER_SCRIPT.sendNotification(env.NOTIFICATION_EMAIL, "Build Status: ${buildStatus}", emailBody)
                }
            }
        }
    }
}