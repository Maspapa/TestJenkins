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
            build job: 'Prod'
        }
    }
}