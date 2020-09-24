Feature: Testing a REST API
  Hello endpoint

  Scenario: GET hello
    When call GET "/hello"
    Then status 200
    Then response is a json {"message":"hello world!"}

  Scenario: POST hello
    When call POST "/hello" with json content {"name":"John"}
    Then status 200
    Then response is a json {"message":"hello John!"}