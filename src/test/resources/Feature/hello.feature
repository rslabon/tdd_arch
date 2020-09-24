Feature: Testing a REST API
  Hello endpoint

  Scenario: hello
    When call GET "/hello"
    Then status 200 and response "Hello World!"