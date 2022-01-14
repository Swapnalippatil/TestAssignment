Feature: Test API for weather
    
    Background:
    Scenario: Test API for weather 
    Given Weather station URL is running
    
    Scenario: Test api without api key
    When User makes a request without API key 
    Then Response code is 401
    
    Scenario: Test api with valid api key
    When User makes a request with valid API key 
    Then Response code is 200
    
    Scenario: Test api with invalid api key
    When User makes a request with invalid API key 
    Then Response code is 401
    
    
    
    
