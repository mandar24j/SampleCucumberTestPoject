Feature: Smoke test of Maantic Sales Order Management Application

  @smoke
  Scenario Outline: Create new lead and verify it in Pega
    Given user is on home page
    When user creates a new lead "<firstName>", "<lastName>","<ultimateParentAccount>","<globalMarket>","<accountName>","<products>","<salesStage>","<estContractValue>","<dealType>","<estimatedDealCloseDate>","<industry>","<region>","<customCategory>","<partnerInvolved>"
    Then user verifies the new lead is created successfully "<accountName>", "<industry>","<dealType>","<salesStage>"
#    When user login into Pega Application "<pegaUser>","<pegaPassword>"
#    Then user verifies new lead "<firstName>", "<lastName>"is populated

    Examples:
      | firstName | lastName | ultimateParentAccount | globalMarket | accountName | products                          | salesStage | estContractValue | dealType | estimatedDealCloseDate | industry   | region | customCategory | partnerInvolved | pegaUser | pegaPassword |
      | John      | Smith_   | AIG                   | EMEA         | JPMC        | Controllers - Catalyst Controller | Engagement | 3                | New      | 10                     | BFS        | East   | Small          | Yes             | Appian   | rules@123    |
#      | Peter     | Parker_  | Google Inc            | APAC         | HSBC        | Routers - ASR Series              | Shaping    | 4                | Existing | 15                     | Healthcare | West   | Medium         | No              | Appian   | rules@123    |