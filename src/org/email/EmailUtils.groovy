package org.email;

class EmailUtils implements Serializable  {
	def script
	EmailUtils(script) {
		this.script = script
	}
    def sendEmail(recipientsList,subject, mimeType, body,attachFilePath) {
	    println("attachement file path=$attachFilePath")
		emailext 	attachmentsPattern: attachFilePath, 
					body: body, 
					mimeType: mimeType, 
					subject: subject, 
					to: recipientsList.join(",")
    }

    def getEmailSubject( status) {
		 return  " $status : ${this.script.proj_name} - ${this.script.git_hash} - ${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} " 
    }

    def getEmailBody( status) {
	    println( "'${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} - ${this.script.git_hash}'" )
        return "<p> $status: Job '${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} - ${this.script.git_hash}':</p> <p>Check console output at &QUOT;<a href='${this.script.env.BUILD_URL}'>${this.script.env.JOB_NAME} [${this.script.env.BUILD_NUMBER}]</a>&QUOT;</p>"
    }
}