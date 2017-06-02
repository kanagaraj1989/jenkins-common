package org.email;

class EmailUtils implements Serializable  {
	private def script
	private def proj_name
	private def git_hash
	private def subject
	private def body
	private def mimeType

	EmailUtils(script) {
		this.script = script
		this.mimeType = 'text/plain'
	}
	
	def setProjectName(proj_name) {
		this.proj_name = proj_name
	}

	def setGitHash(git_hash) {
		this.git_hash = git_hash
	}
    
	def setMimeType(mimeType) {
		this.mimeType = mimeType
	}

    def sendEmail(recipientsList,attachFilePath) {
	    println("attachement file path=$attachFilePath")
		this.script.emailext  attachmentsPattern: attachFilePath, 
					body: this.body, 
					mimeType: this.mimeType, 
					subject: this.subject,  
					to: recipientsList.join(",")
    }

    def setEmailSubject( status) {
		 this.subject = "$status : ${this.proj_name} - ${this.git_hash} - ${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} " 
    }

    def setEmailBody( status) {
	    println( "'${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} - ${this.git_hash}'" )
        this.body = "<p> $status: Job '${this.script.env.JOB_NAME} - ${this.script.env.BUILD_NUMBER} - ${this.git_hash}':</p> <p>Check console output at &QUOT;<a href='${this.script.env.BUILD_URL}'>${this.script.env.JOB_NAME} [${this.script.env.BUILD_NUMBER}]</a>&QUOT;</p>"
    }
}