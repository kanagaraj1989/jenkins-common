package org.email;

class EmailUtils {
    def sendEmail(recipientsList,subject, mimeType, body,attachFilePath) {
	    println("attachement file path=$attachFilePath")
		emailext 	attachmentsPattern: attachFilePath, 
					body: body, 
					mimeType: mimeType, 
					subject: subject, 
					to: recipientsList.join(",")
    }

    def getEmailSubject( status) {
		return "This is kanagaraj. Welcome to new world."
	    //return  " $status : $PROJ_NAME - ${GIT_HASH} - ${env.JOB_NAME} - ${env.BUILD_NUMBER} " 
    }

    def getEmailBody( status) {
	    println( "'${env.JOB_NAME} - ${env.BUILD_NUMBER} - ${GIT_HASH}'" )
        return "<p> $status: Job '${env.JOB_NAME} - ${env.BUILD_NUMBER} - ${GIT_HASH}':</p> <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"
    }
}