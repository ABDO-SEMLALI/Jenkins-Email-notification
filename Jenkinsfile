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
                // Load workflowlibs.manager Groovy script
                def rootDir = pwd()
                def workflowLibsManager = load "${rootDir}@script/workflowlibs.manager.groovy"

                // Parameters for notifyEmail function
                def buildStatus = currentBuild.result ?: 'UNKNOWN'
                def emailRecipients = [env.NOTIFICATION_EMAIL]

                // Call the notifyEmail function from the loaded Groovy file
                workflowLibsManager.notifyEmail(buildStatus, emailRecipients)
            }
        }

        failure {
            script {
                // Load workflowlibs.manager Groovy script
                def rootDir = pwd()
                def workflowLibsManager = load "${rootDir}@script/workflowlibs.manager.groovy"

                // Parameters for notifyEmail function
                def buildStatus = currentBuild.result ?: 'UNKNOWN'
                def emailRecipients = [env.NOTIFICATION_EMAIL]

                // Call the notifyEmail function from the loaded Groovy file
                workflowLibsManager.notifyEmail(buildStatus, emailRecipients)
            }
        }
    }
}
