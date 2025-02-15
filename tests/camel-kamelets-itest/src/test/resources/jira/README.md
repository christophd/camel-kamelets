# Jira Kamelet test

This test verifies Jira Kamelets.

## Objectives

The test verifies the Jira Kamelet by creating a Camel K integration that uses the Kamelet interacting with the Jira REST API. 
The Jira API is simulated during the test with an Http server endpoint.

### Test Kamelet

The test performs the following high level steps:

*Preparation*
- Create a Jira API server that will simulate the Jira API (e.g. providing a new issue as a response)

*Scenario* 
- Create the Camel K integration that uses the Jira Kamelets
- Wait for the Camel K integration to start and listen for new issue events
- Simulate a new issue returned by the Jira API server
- Verify the issue was logged by Camel K integrations using the log-sink

*Cleanup*
- Delete the Camel K integration

## Installation

The test assumes that you have [JBang](https://www.jbang.dev/) installed and the Citrus CLI setup locally.

You can review the installation steps for the tooling in the documentation:

- [JBang](https://www.jbang.dev/documentation/guide/latest/installation.html)
- [Install Citrus JBang App](https://citrusframework.org/citrus/reference/html/index.html#runtime-jbang-install)

## Run the test

```shell script
$ citrus run src/test/resources/jira/jira-source.it.yaml
$ citrus run src/test/resources/jira/jira-add-issue-sink.it.yaml
```

You can increase number of attempts to run the test by adding: "-e CITRUS_CAMELK_MAX_ATTEMPTS=1000"

You will be provided with the test log output and the test results.
