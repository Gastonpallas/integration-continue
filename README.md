# Porjet Intégration continue avec Jenkins et Sonar

Ce projet est un exemple d'intégration continue utilisant Jenkins, Maven, et SonarQube.

## Prérequis

- Jenkins
- Maven
- SonarQube

## Pipeline Jenkins

Voici le script du pipeline Jenkins utilisé pour ce projet :

```groovy
pipeline {
    agent any

    tools {
        // Installer la version de Maven configurée en tant que "Maven" et l'ajouter au chemin.
        maven "Maven"
    }

    stages {
        stage('Build') {
            steps {
                // Récupérer du code depuis un dépôt GitHub
                git branch: 'master', url: 'https://github.com/Gastonpallas/integration-continue.git'

                // Exécuter Maven sur un agent Unix.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // Pour exécuter Maven sur un agent Windows, utiliser
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // Si Maven a pu exécuter les tests, même si certains ont échoué,
                // enregistrer les résultats des tests et archiver le fichier JAR.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('SonarQube analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQube'
                    withSonarQubeEnv('SonarQube') {
                        sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.token=sqp_2d7d3817080a84e0acd9f0ed663d768ce219280e \
                        -Dsonar.projectKey=CICD \
                        -Dsonar.host.url=http://sonarqube:9000/ \
                        -Dsonar.java.binaries=target/classes
                        """
                    }
                }
            }
        }
    }
}
