pipeline {
    agent any

    environment {
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
    }

    post {
        success {
            script {
                // Load HTML Groovy script template
                def emailHtmlTemplate = readFile('groovy-html.template')

                // Set subject and body
                def buildStatus = currentBuild.result ?: 'UNKNOWN'
                def subject = "Build Status: ${buildStatus}"
                def body = emailHtmlTemplate.replaceAll('\${BUILD_STATUS}', buildStatus)

                // Send email notification
                sendEmailNotification(subject, body)
            }
        }

        failure {
            script {
                // Load Text Groovy script template
                def emailTextTemplate = readFile('groovy-text.template')

                // Set subject and body
                def buildStatus = currentBuild.result ?: 'UNKNOWN'
                def subject = "Build Status: ${buildStatus}"
                def body = emailTextTemplate.replaceAll('\${BUILD_STATUS}', buildStatus)

                // Send email notification
                sendEmailNotification(subject, body)
            }
        }
    }

}

def sendEmailNotification(subject, body) {
    emailext body: body, subject: subject, to: env.NOTIFICATION_EMAIL
}
