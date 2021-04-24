$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/LogIn.feature");
formatter.feature({
  "name": "sample Test",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "As a seller, i need to log onto platform using services",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@test"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "As a seller after registration with credentials available",
  "keyword": "Given "
});
formatter.match({
  "location": "sampleStepDef.logIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "log into platform using services",
  "keyword": "Then "
});
formatter.match({
  "location": "sampleStepDef.verify()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Verify the token id is generated",
  "keyword": "And "
});
formatter.match({
  "location": "sampleStepDef.sample()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});