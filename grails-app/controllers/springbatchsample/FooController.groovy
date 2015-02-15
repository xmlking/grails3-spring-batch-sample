package springbatchsample

import org.grails.core.artefact.ControllerArtefactHandler
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder

class FooController {
    def jobLauncher
    def simpleJob
    def simpleJob2
    def simpleJob3
    def simpleJob4
    def simpleJob5
    def multiStepJob
    def cvsGormJob

    def index() {
        def actions = new HashSet<String>()
        def controllerClass = grailsApplication.getArtefactInfo(ControllerArtefactHandler.TYPE).getGrailsClassByLogicalPropertyName(controllerName)
        controllerClass.getActions().each { actionName ->
            if (actionName != "index") actions.add(actionName)
        }
        [actions:actions]
    }

    def launchSimpleJob() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test SimpleJob").toJobParameters();
        jobLauncher.run(simpleJob, jobParameters)
        render(view: "launch", model: [message:"SimpleJob launched..."])
    }

    def launchSimpleJob2() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test SimpleJob2").toJobParameters();
        jobLauncher.run(simpleJob2, jobParameters)
        render(view: "launch", model: [message:"SimpleJob2 launched..."])
    }

    def launchSimpleJob3() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test SimpleJob3").toJobParameters();
        jobLauncher.run(simpleJob3, jobParameters)
        render(view: "launch", model: [message:"SimpleJob3 launched..."])
    }

    def launchSimpleJob4() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test SimpleJob4").toJobParameters();
        jobLauncher.run(simpleJob4, jobParameters)
        render(view: "launch", model: [message:"SimpleJob4 launched..."])
    }

    def launchSimpleJob5() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test SimpleJob5").toJobParameters();
        jobLauncher.run(simpleJob5, jobParameters)
        render(view: "launch", model: [message:"SimpleJob5 launched..."])
    }

    def launchMultiStepJob() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test MultiStepJob").toJobParameters();
        jobLauncher.run(multiStepJob, jobParameters)
        render(view: "launch", model: [message:"MultiStepJob launched..."])
    }

    def launchCvsGormJob() {
        JobParameters jobParameters = new JobParametersBuilder().addDate("now",
                new Date()).addString("comment", "Unit Test CvsGormJob").toJobParameters();
        jobLauncher.run(cvsGormJob, jobParameters)
        render(view: "launch", model: [message:"CvsGormJob launched..."])
    }
}