job("documentation-example") {
    scm {
        git {
            remote {
                url("git@github.com:WrRaThY/documenting-project.git")
                credentials("github")
                name("origin")
            }
            branch("*/master")
        }
    }
    steps {
        maven('clean package -Pgenerate-html-docs -DskipTests', 'documentation/pom.xml')
    }
    publishers {
        publishHtml {
            report('documentation/target/generated-docs') {
                reportName('docs')
                reportFiles('docs.html')
                keepAll(false)
                alwaysLinkToLastBuild(false)
            }
        }
    }
}
