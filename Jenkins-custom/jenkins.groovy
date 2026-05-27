pipeline {
    agent any

    stages {
        stage('Checkout Git') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/gopikrishnanqa/jenkins_projects_test.git'
                    ]]
                ])
            }
        }

        stage('Validate PR Title') {
            when {
                expression {
                    return env.CHANGE_ID != null && env.CHANGE_TITLE != null
                }
            }
            steps {
                script {
                    def title = env.CHANGE_TITLE?.trim() ?: ''
                    echo "PR title: ${title}"
                    def valid = title ==~ /^(fix|feat):\s+[A-Z]+-\d+\b.*$/

                    if (!valid) {
                        error(
                            "Invalid PR title. Use the format `fix: JIRA-123 description` or `feat: JIRA-123 description`. " +
                            "Actual title: '${title}'"
                        )
                    }
                }
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