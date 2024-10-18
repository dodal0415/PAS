pipeline {
    agent any

    stages {
        stage('github clone') {
            steps{
                checkout(
                    [$class: 'GitSCM',
                    branches: [[name: '*//back/feat/AR']],
                    extensions:
                    [[$class: 'SubmoduleOption',
                        disableSubmodules: false,
                        parentCredentials: true,
                        recursiveSubmodules: false,
                        reference: '',
                        trackingSubmodules: true]],
                    userRemoteConfigs:
                        [[credentialsId: 'github_gom5314',
                            url: 'https://github.com/Gom534/PAS.git']]
                    ]
                )
            }
        }

        stage('build'){
            steps{https://github.com/Gom534/PAS.git
                dir('backend'){
                    sh'''
                        echo build start
                        ./gradlew clean bootJar
                    '''
                }
            }
        }
    }
}
