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
        stage('Check for Changes in build/sample') {
            steps {
                script {
                    def changes = sh(script: "git diff --name-only HEAD~1..HEAD", returnStdout: true).trim()
                    if (changes.contains("README")) {
                        echo "Jenkinsfile in build/sample has changed, proceeding with the build."
                    } else {
                        echo "No changes in build/sample/Jenkinsfile, skipping build."
                        currentBuild.result = 'SUCCESS'  // 直接跳过构建
                        return  // 退出 pipeline
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