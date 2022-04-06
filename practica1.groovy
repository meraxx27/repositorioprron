job('practica1') {
  description('practica1 para el curso de Jenkins')
  scm{
    git('https://github.com/meraxx27/repositorioprron.git','main'){ node ->
      node / gitConfigName('meraxx27')
      node / gitConfigEmail('fv063105@gmail.com')
    }
  }
      parameters {
        stringParam('nombre', defaultValue = 'Julian', description = 'Parameto de cadena para el Job Booleano')
        choiceParam('Planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano'])
        booleanParam('agente', false)
    }
    triggers {
        cron('H/7 * * * *')
    }
    steps {
        shell("bash jobscript.sh")
    }
    publishers {
        mailer('fv063105@gmail.com', true, true)
        mailer('cuentajenkins8@gmail.com', true, true)
        slackNotifier {
            notifyAborted(true)
		        notifyEveryFailure(true)
		        notifyNotBuilt(false)
		        notifyUnstable(false)
		        notifyBackToNormal(true)
		        notifySuccess(false)
		        notifyRepeatedFailure(false)
		        startNotification(false)
		        includeTestSummary(false)
		        includeCustomMessage(false)
		        customMessage(null)
		        sendAs(null)
		        commitInfoChoice('NONE')
		        teamDomain(null)
		        authToken(null)
        }
    }
}
