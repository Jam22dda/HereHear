pipeline {
    agent any
    stages {
        stage("Set Variable") {
            steps {
                script {
                    IMAGE_NAME_BE = "herehear-prod-springboot"
                    APPLICATION_YML_PATH = "/usr/spring/prod/resources"
                    CONTAINER_NAME_BE = "herehear_prod_be"
                    PROJECT_DIR_BE = "backend/HereHear"
                }
            }
        }

        // 설정 파일 참조
        stage("copy yml") {
            steps {
                sh "cp -r ${APPLICATION_YML_PATH} ${PROJECT_DIR_BE}/src/main"
                sh "cp -r ${APPLICATION_YML_PATH} ${PROJECT_DIR_BE}/src/test"
            }
        }


        stage('SonarQube analysis') {
            steps{
                withSonarQubeEnv('SonarQube'){
                    sh """
                    cd ${PROJECT_DIR_BE}
                    chmod 777 ./gradlew
                    ./gradlew sonar\
                     -Dsonar.projectKey=herehear_prod_be \
                     -Dsonar.projectName='herehear_prod_be' \
                     -Dsonar.profile='Sonar way' \
                     -Dsonar.host.url=http://3.34.42.38:9000 \
                     -Dsonar.token=sqp_035414fe525cacb63c0343cdf68da14ad98b3578
                    """
                }
            }
        }

        // 백엔드 프로젝트 빌드
        stage("be build") {
            // build
            steps {
                sh """
                cd ${PROJECT_DIR_BE}
                chmod 777 ./gradlew
                ./gradlew clean build
                """
            }
        }

        // 컨테이너 클리닝
        stage("container cleaning") {
            steps {
                sh "docker ps -q -f name=${CONTAINER_NAME_BE} | xargs --no-run-if-empty docker container stop"
                sh "docker container ls -a -q -f name=${CONTAINER_NAME_BE} | xargs --no-run-if-empty docker rm"
            }
        }

        // 이미지 삭제
        stage("image cleaning") {
            steps {
                sh "docker images ${IMAGE_NAME_BE} -q | xargs -r docker rmi -f"
            }
        }

        // 도커 이미지 빌드
        stage("be image build") {
            steps {
                sh """
                cd ${PROJECT_DIR_BE}
                docker build --no-cache -t ${IMAGE_NAME_BE} .
                """
            }
        }

        // 컨테이너 실행
        stage("be container run") {
            steps {
                sh "docker run -d -p 8080:8080 -v herehearprodvolume:/app/file --name ${CONTAINER_NAME_BE} ${IMAGE_NAME_BE}"
            }
        }

        // 도커 레이어 정리
        stage("docker layer pruning") {
            steps {
                script {
                    sh "docker system prune -a -f"
                }
            }
        }
    }
}