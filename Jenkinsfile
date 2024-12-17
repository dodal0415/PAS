pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Jenkins에 저장된 자격 증명 ID
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gom534/PAS.git', credentialsId: 'github_gom5314', branch: 'back/deploy'
                sh 'ls -la'
                sh 'pwd'
                sh 'cd back'
            }
        }

        stage('Prepare YAML File') {
            steps {
               
                    // Credentials로 저장된 yml 내용을 파일로 생성
                    withCredentials([file(credentialsId: 'artionyml1', variable: 'YML_CONTENT')]) {
                     script {
                        sh 'chmod 755 -f back/src/main/resources/application.yml'
                        sh 'cp $YML_CONTENT back/src/main/resources/application.yml'
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {                 
                    sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"

                    // Docker 이미지 빌드
                    sh 'docker build -t wjddntyvld/forcatmung:latest -f back/Dockerfile .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Docker 이미지 푸시
                    sh 'docker push wjddntyvld/forcatmung:latest'
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    // 최신 이미지를 pull합니다.
                    sh 'docker pull wjddntyvld/forcatmung:latest'       
                   sh '''
                        containers=$(docker ps -q --filter "publish=8000")
                        echo "${containers}" | xargs docker stop
                        echo "${containers}" | xargs docker rm
                    '''   
                    // 새로운 이미지를 기반으로 컨테이너를 실행합니다.
                    sh 'docker run -d -p 8000:8000 wjddntyvld/forcatmung:latest'
                }
            }
        } 
    }
}
