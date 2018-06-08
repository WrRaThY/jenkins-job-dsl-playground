job("zz_enable_js_and_css_for_reports") {
    description('disables js and css blocking')
    configure {
        it / triggers / 'org.jvnet.hudson.plugins.triggers.startup.HudsonStartupTrigger' {
            'spec'()
            'quietPeriod'(0)
            'runOnChoice'('ON_BOTH')
        }

        it / builders / 'hudson.plugins.groovy.SystemGroovy' {
            'source'(class: "hudson.plugins.groovy.StringSystemScriptSource") {
                'script' {
                    'script'('System.setProperty("hudson.model.DirectoryBrowserSupport.CSP","")')
                    'sandbox'(false)
                }
            }
        }
    }
}
