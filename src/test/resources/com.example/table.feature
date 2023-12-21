Feature: Table inside scenario

  @positive @ignored
  Scenario: Listing book details
    Given the following books
      | title                  | author              | yearOfPublishing |
      | To kill a mockingbird  | Harper Lee          | 1960             |
      | The catcher in the rye | J.D. Salinger       | 1951             |
      | The great Gatsby       | F. Scott Fitzgerald | 1925             |

  Scenario: Treating placeholders in tables
    Given the following table
      | key         | value         |
      | date        | today         |
      | number      | randomNumber  |
      | stringOne   | emptyString   |
      | stringTwo   | null          |
      | stringThree | textOfElement |