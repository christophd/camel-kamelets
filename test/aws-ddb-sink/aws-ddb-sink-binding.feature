Feature: AWS DDB Kamelet - binding to URI

  Background:
    Given Kamelet aws-ddb-sink is available
    Given Camel K resource polling configuration
      | maxAttempts          | 200   |
      | delayBetweenAttempts | 2000  |
    Given variables
      | aws.ddb.tableName | userData |
      | aws.ddb.operation | PutItem |
      | aws.ddb.json.data | {"name":"christoph","country":49} |

  Scenario: Start LocalStack container
    Given Enable service DYNAMODB
    Given start LocalStack container
    And log 'Started LocalStack container: ${YAKS_TESTCONTAINERS_LOCALSTACK_CONTAINER_NAME}'

  Scenario: Create AWS-DDB client
    Given New global Camel context
    Given load to Camel registry amazonDDBClient.groovy

  Scenario: Create AWS-DDB Kamelet sink binding
    When load KameletBinding aws-ddb-sink-binding.yaml
    And KameletBinding aws-ddb-sink-binding is available
    And Camel K integration aws-ddb-sink-binding is running

  Scenario: Verify Kamelet sink
    Given Camel consumer timeout is 60000 ms
    Given expect Camel exchange body: ${aws.ddb.json.data}
    Then verify Camel exchange from("aws2-ddbstream://${aws.ddb.tableName}?amazonDynamoDbStreamsClient=#amazonDDBClient&maxResultsPerRequest=1")

  Scenario: Remove Camel K resources
    Given delete KameletBinding aws-ddb-sink-binding

  Scenario: Stop container
    Given stop LocalStack container
