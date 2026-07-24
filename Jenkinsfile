pipeline {
    agent any

    parameters {

        booleanParam(
            name: 'DEPLOY_APP',
            defaultValue: false,
            description: 'Deploy Application'
        )

        booleanParam(
            name: 'DEPLOY_DB',
            defaultValue: false,
            description: 'Deploy Database'
        )

        booleanParam(
            name: 'REMOVE_APP',
            defaultValue: false,
            description: 'Remove Application'
        )

        booleanParam(
            name: 'REMOVE_DB',
            defaultValue: false,
            description: 'Remove Database'
        )
    }

    tools {
        maven 'maven'
    }

    environment {
        IMAGE_NAME = "sanjanaspuranik/springboot-app:v1"
    }

    stages {

        stage('Build JAR') {
            when {
                expression { params.DEPLOY_APP }
            }
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            when {
                expression { params.DEPLOY_APP }
            }
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Login') {
            when {
                expression { params.DEPLOY_APP }
            }
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { params.DEPLOY_APP }
            }
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Deploy Database') {
            when {
                expression { params.DEPLOY_DB }
            }
            steps {
                sh 'kubectl apply -f kubernetes/'
            }
        }

        stage('Deploy Application') {
            when {
                expression { params.DEPLOY_APP }
            }
            steps {
                sh '''
                kubectl apply -f kubernetes/ns.yml
                kubectl apply -f kubernetes/service.yml
                kubectl apply -f kubernetes/deploy.yml
                '''
            }
        }

        stage('Remove Application') {
            when {
                expression { params.REMOVE_APP }
            }
            steps {
                sh 'kubectl delete -f kubernetes/ --ignore-not-found'
            }
        }

        stage('Remove Database') {
            when {
                expression { params.REMOVE_DB }
            }
            steps {
                sh 'kubectl delete -f kubernetes/ --ignore-not-found'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully.'
        }
        failure {
            echo 'Pipeline execution failed.'
        }
        always {
              echo "Build completed"
        }
    }
}
