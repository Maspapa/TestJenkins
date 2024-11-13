@Library('setBranch@v1.0') _  // 引用全局配置的共享库

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
        
        stage('Set Env') {
            steps {
                    script {
                        
                        echo "The value of BRANCH_NAME is: ${env.BRANCH_NAME}"
                        var branch = setBranch(env.BRANCH_NAME)
                        echo "The value of myVar is: ${branch}"
                        env.GCDS_Branch = branch
                    }
            }
        }
        stage('Git Checkout') { 
            steps {

                dir("$WORKSPACE"){
                    checkout scm: scmGit(
                        branches: [[name: "$GCDS_Branch"]],
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