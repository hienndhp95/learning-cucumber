pipeline {
  agent any
  stages {
    stage('Build Source') {
      steps {
        bat 'mvn clean'
      }
    }

    stage('Run Chrome') {
      parallel {
        stage('Run Chrome') {
          steps {
            bat 'mvn test -DBROWSER=chrome verify'
          }
        }

        stage('Run Firefox') {
          steps {
            bat 'mvn test -DBROWSER=firefox verify'
          }
        }

      }
    }

    stage('Generate Report') {
      steps {
        bat 'cucumber buildStatus: \'null\',              customCssFiles: \'\',              customJsFiles: \'\',             failedFeaturesNumber: -1,              failedScenariosNumber: -1,              failedStepsNumber: -1,              fileIncludePattern: \'**/*.json\',              pendingStepsNumber: -1,              skippedStepsNumber: -1,              sortingMethod: \'ALPHABETICAL\',              undefinedStepsNumber: -1'
      }
    }

  }
}