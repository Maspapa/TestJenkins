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
                withFolderProperties {
                    script {
                        switch (env.BRANCH_NAME) {
                            case 'dev':
                                env.GCDS_Branch = "$BRANCH_NAME"
                                break
                            case 'Prod':
                                env.GCDS_Branch = "$BRANCH_NAME"
                                break
                            default:
                                env.GCDS_Branch = "test"
                                break
                        }
                    }
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