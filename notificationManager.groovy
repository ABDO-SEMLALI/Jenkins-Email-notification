package workflowlibs.manager;

import groovy.text.StreamingTemplateEngine

/**
 * This method returns a string with the template filled with Groovy variables
 */
def emailTemplate(params) {
    def fileName = "email.html.groovy"
    
    // Instantiate StreamingTemplateEngine
    def engine = new StreamingTemplateEngine()

    // Use Java's Files class to read the file with UTF-8 encoding
    def fileContentsBytes = libraryResource(fileName).read()
    def fileContents = new String(fileContentsBytes, "UTF-8")

    return engine.createTemplate(fileContents).make(params).toString()
}

/**
 * This method sends an email generated with data from Jenkins
 * @param buildStatus String with job result
 * @param emailRecipients Array with emails: emailRecipients = []
 */
def notifyEmail(buildStatus, emailRecipients) {
    try {
        def icon = "✅"
        def statusSuccess = true
        def hasArtifacts = true

        if (buildStatus != "SUCCESSFUL") {
            icon = "❌"
            statusSuccess = false
            hasArtifacts = false
        }

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

return this;
