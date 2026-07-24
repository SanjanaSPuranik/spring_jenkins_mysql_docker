pipeline {
    agent any

    parameters {
        choice(
            name: 'ACTION',
            choices: ['DEPLOY', 'REMOVE'],
            description: 'Deploy or Remove Kubernetes resources'
        )
    }

    tools {
        maven 'maven'
    }

    environment {
        IMAGE_NAME = "YOUR_DOCKERHUB_USERNAME/springboot-app:v1"
    }

    stages {

        stage('Build JAR') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Login') {
            when {
                expression { params.ACTION == 'DEPLOY' }
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
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Deploy to Kubernetes') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'kubectl apply -f kubernetes/'
            }
        }

        stage('Remove from Kubernetes') {
            when {
                expression { params.ACTION == 'REMOVE' }
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
            cleanWs()
        }
    }
}