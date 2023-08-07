Feature: Automated DemoBlaze E-commerce Tests

  Scenario: adding all products to the cart
    Given I am on the product page
    Then I add all the product to the cart

  Scenario: Delete all products from the cart except "Samsung galaxy s6"
    Given I am on the cart page
    When I delete all products from the cart except "Samsung galaxy s6"
    Then Only "Samsung galaxy s6" should be left in the cart

  Scenario Outline: Place an order with valid and invalid credit card information
    Given I am on the cart page
    When I place an order with "<name>", "<country>", "<city>", "<cardNumber>", "<year>", and "<month>"
		Then I validate the output with "<validData>"
    Examples: 
      | name       | country        | city     | cardNumber       | year | month | validData |
      | John Doe   | United States  | New York | 1234567890123456 | 2025 |    12 | true      |
      | Jane Smith | United Kingdom | London   | 9abedefgh        | 2024 |    08 | false     |
