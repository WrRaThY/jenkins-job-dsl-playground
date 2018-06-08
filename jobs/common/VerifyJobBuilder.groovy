package common

import javaposse.jobdsl.dsl.DslFactory

class VerifyJobBuilder {

    private final DslFactory dslFactory
    private String projectName
    private String gitProjectName
    private String mvn = 'clean verify'
    private String location = '**/target/surefire-reports/*.xml, **/target/failsafe-reports/*.xml'

    private VerifyJobBuilder(DslFactory dslFactory) {
        this.dslFactory = dslFactory
    }

    static VerifyJobBuilder builder(DslFactory dslFactory) {
        return new VerifyJobBuilder(dslFactory)
    }

    VerifyJobBuilder projectName(String projectName) {
        this.projectName = projectName
        this
    }

    VerifyJobBuilder gitProjectName(String gitProjectName) {
        this.gitProjectName = gitProjectName
        this
    }

    VerifyJobBuilder mavenCommand(String mvn) {
        this.mvn = mvn
        this
    }

    VerifyJobBuilder junitPublisherReportLocation(String location) {
        this.location = location
        this
    }

    void build() {
        dslFactory.job("$projectName-verify") {
            scm {
                git {
                    remote {
                        url("git@github.com:WrRaThY/${gitProjectName}.git")
                        credentials("github")
                        name("origin")
                    }
                    branch("*/master")
                }
            }
            steps {
                maven("$mvn")
            }
            publishers {
                archiveJunit("$location") {
                    allowEmptyResults(true)
                    retainLongStdout(false)
                    healthScaleFactor(1)
                }
            }
        }
    }

}
