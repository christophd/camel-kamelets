# ---------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# ---------------------------------------------------------------------------

Feature: Extract field Kamelet action

  Background:
    Given HTTP server timeout is 15000 ms
    Given HTTP server "test-service"
    Given start HTTP server
    Given variable field = "subject"

  Scenario: Create Http server
    Given create Kubernetes service test-service with target port 8080
    Given purge endpoint test-service

  Scenario: Create Kamelet binding
    Given variable input is
    """
    { "id": "citrus:randomUUID()", "${field}": "Camel K rocks!" }
    """
    When load Pipe extract-field-action-pipe.yaml
    Then Camel K integration extract-field-action-pipe should be running
    And Camel K integration extract-field-action-pipe should print Routes startup

  Scenario: Verify output message sent
    Given expect HTTP request body: "Camel K rocks!"
    When receive POST /result
    Then send HTTP 200 OK

  Scenario: Remove resources
    Given delete Pipe extract-field-action-pipe
    And delete Kubernetes service test-service
    And stop HTTP server
