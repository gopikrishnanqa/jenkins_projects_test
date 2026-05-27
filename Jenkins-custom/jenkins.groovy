pipeline {
    agent any

    stages {
        stage('Checkout Git') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/example/repo.git'
                    ]]
                ])
            }
        }

        stage('Print Info') {
            steps {
                script {
                    println "Checked out repository to: ${pwd()}"
                    sh 'echo "Files in workspace:"'
                    sh 'ls -la'
                }
            }
        }
    }
}