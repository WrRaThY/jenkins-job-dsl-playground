List plugins = [
        'git-client',
        'git',
        'job-dsl',
        'parameterized-trigger',
        'build-with-parameters',
        'delivery-pipeline-plugin',
        'build-pipeline-plugin',
        'gradle',
        'maven-plugin',
        'timestamper',
        'gitlab-hook',
        'gitlab-plugin',
        'ssh',
        'groovy',
        'jobConfigHistory',
        'startup-trigger-plugin',
        'htmlpublisher'
]

def pluginManager = PluginManager.createDefault(Jenkins.instance)
pluginManager.install(plugins, true)
