package org.email;

class EmailUtils implements Serializable  {
	private def script
	private def proj_name
	private def git_hash
	
	EmailUtils(script) {
		this.script = script
	}
	
	def setProjectName(proj_name) {
		this.proj_name = proj_name
	}

	def setGitHash(git_hash) {
		this.git_hash = git_hash
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
		 return  " $status : ${this.proj_name} - ${this.git_hash} - ${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} " 
    }

    def getEmailBody( status) {
	    println( "'${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} - ${this.git_hash}'" )
        return "<p> $status: Job '${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} - ${this.git_hash}':</p> <p>Check console output at &QUOT;<a href='${this.script.env.BUILD_URL}'>${this.script.env.JOB_NAME} [${this.script.env.BUILD_NUMBER}]</a>&QUOT;</p>"
    }
}