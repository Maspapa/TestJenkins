pipeline {
    agent any
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
                        branches: [[name: "dev"]], 
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
        }
    }
}