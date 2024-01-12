package workflowlibs.manager

import groovy.text.StreamingTemplateEngine
import java.nio.charset.StandardCharsets

final SUCCESS_ICON = "✅"
final FAILURE_ICON = "❌"

def emailTemplate(params) {
    def fileName = "email.html.groovy"
    def fileContents = libraryResource(fileName).getText(StandardCharsets.UTF_8)
    def engine = new StreamingTemplateEngine()

    return engine.createTemplate(fileContents).make(params).toString()
}

def sendNotification(buildStatus, emailRecipients) {
    try {
        def icon = buildStatus == "SUCCESS" ? SUCCESS_ICON : FAILURE_ICON
        def statusSuccess = buildStatus == "SUCCESS"
        def hasArtifacts = statusSuccess // You may need to adjust this based on your criteria

        def body = emailTemplate([
            "jenkinsText"   :   env.JOB_NAME,
            "jenkinsUrl"    :   env.BUILD_URL,
            "statusSuccess" :   statusSuccess,
            "hasArtifacts"  :   hasArtifacts,
            "downloadUrl"   :   "www.downloadurl.com"
        ])

        mail(to: emailRecipients.join(","),
            subject: "${icon} [ ${env.JOB_NAME} ] [${env.BUILD_NUMBER}] - ${buildStatus} ",
            body: body,
            mimeType: 'text/html'
        )
    } catch (e) {
        println "ERROR SENDING EMAIL ${e}"
    }
}
