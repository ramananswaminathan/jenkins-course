job('NodeJS Docker example') {
    scm {
        git('git://github.com/ramananswaminathan/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('ramananswaminathan')
            node / gitConfigEmail('schwami.iyer@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('krepo:5000/nodejs')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('krepo')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
