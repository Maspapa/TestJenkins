pipeline {
    agent any
    
    triggers {
        pollSCM('*/1 * * * *')
    }
    stages {
        stage('Clean Workspace') { 
            steps {
                cleanWs()
            }
        }
        stage('Check for changes in build folder') {
            steps {
                script {
                    def changes = sh(script: "git diff --name-only HEAD~1 HEAD", returnStdout: true).trim()
                    echo "Changed files: ${changes}"
                    if (!changes.contains('README.md')) {
                        currentBuild.result = 'ABORTED'
                        error("No changes in README.md, aborting the build")
                    }
                }
            }
        }
        stage('Git Checkout') { 
            steps {

                dir("$WORKSPACE"){
                    checkout scm: scmGit(
                        branches: [[name: "$BRANCH_NAME"]],
                        extensions: [], 
                        userRemoteConfigs: [
                            [url: 'https://github.com/Maspapa/TestCode.git']
                        ]
                    )
                }
            }
        }

    }
    post {
        success {
            archiveArtifacts artifacts: '**/*', fingerprint: true, onlyIfSuccessful: false, defaultExcludes: false
            build job: "Mason/2_sample/$BRANCH_NAME", wait: false
        }
    }
}