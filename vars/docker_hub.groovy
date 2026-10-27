
def call(String credId, String imageName) {

    withCredentials([usernamePassword(
        credentialsId: "${credId}",
        passwordVariable: 'dockerHubPass',
        usernameVariable: 'dockerHubUser'
    )]) {

        sh '''
            echo "$dockerHubPass" | docker login -u "$dockerHubUser" --password-stdin
        '''

        sh "docker image tag ${imageName} ${env.dockerHubUser}/${imageName}:latest"

        sh "docker push ${env.dockerHubUser}/${imageName}:latest"
    }
}
