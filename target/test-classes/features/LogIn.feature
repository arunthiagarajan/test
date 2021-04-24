Feature: sample Test

  @test
  Scenario: As a seller, i need to log onto platform using services
    Given As a seller after registration with credentials available
    Then  log into platform using services
    And   Verify the token id is generated

  @test1
  Scenario: As a seller, i need to log onto platform
    Given Log In Into Platform
    And  Verify Dashboard page is Displayed