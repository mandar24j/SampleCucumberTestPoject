Feature: Vacation Tab Scenarios

  Scenario Outline: Search Flights , hotels and car
    Given user is on home page
    When user navigate to Vacation tab
    And user selects Flight, Hotel and "<Car>"
    And user enters "<origin>", "<destination>" locations
    And user enters "<departingDaysFromToday>", "<departureTime>","<returningDaysFromToday>", "<returningTime>"
    Then user gets atleast one search result

    Examples: 
      | Car  | origin | destination | departingDaysFromToday | departureTime | returningDaysFromToday | returningTime |
      | True | SFO    | LAX         |                      1 | evening       |                     21 | morning       |
      | True | SFO    | LAX         |                      2 | morning       |                     14 | morning       |
      | True | LAX    | NYC         |                      3 | evening       |                      7 | evening       |
      | True | LAX    | NYC         |                     10 | morning       |                     28 | morning       |
