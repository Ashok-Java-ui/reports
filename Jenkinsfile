properties([parameters([gitParameter(branch: '', branchFilter: '.*', defaultValue: 'master', description: 'select branch to build', name: 'BRANCH', quickFilterEnabled: false, selectedValue: 'NONE', sortMode: 'NONE', tagFilter: '*', type: 'PT_BRANCH')])])
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}