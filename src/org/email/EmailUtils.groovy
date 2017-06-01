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

    def getEmailSubject( status,proj_name,git_hash) {
		 return  " $status : $proj_name - $git_hash - ${env.JOB_NAME} - ${env.BUILD_NUMBER} " 
    }

    def getEmailBody( status,git_hash) {
	    println( "'${env.JOB_NAME} - ${env.BUILD_NUMBER} - $git_hash'" )
        return "<p> $status: Job '${env.JOB_NAME} - ${env.BUILD_NUMBER} - $git_hash':</p> <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"
    }
}