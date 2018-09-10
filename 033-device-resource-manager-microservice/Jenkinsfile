@Library('JenkinsLib') _

buildApplicationContainerized {
    moduleDir = "deviceresourcemanager-ms"
    repositoryUrl = "https://jenkins-service-account@fleet.alm.accenture.com/avsbitbucket/scm/avs-m3/033-device-resource-manager-microservice.git"
    version = "6.3"
    namespaceOCP = "dev-63"
    gitBranchDevOps = "release/AVS_6.3" 
    repositoryUrlDevOpsOCP = "https://fleet.alm.accenture.com/avsbitbucket/scm/acd/deviceresourcemanagerms.git"
    mavenAdditionalParams = "-P distributor"
}