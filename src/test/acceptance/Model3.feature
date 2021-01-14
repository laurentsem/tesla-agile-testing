Feature: Model3
  Scenario Outline: Caractéristiques Tesla Model3
    Given je suis sur la page model3
    When je sélectionne "caractéristiques"
    When je clique sur la gamme est "<Gamme>"
      And le poids est "<Poids>"
      And l'accélération est "<Accélération>"
      And l'autonomie est "<Autonomie>"
    Examples:
      | Gamme                 |  Poids   | Accélération | Autonomie |
      | Performance           | 1,844 kg | 3,3 secondes | 567 km    |
      | Grande Automonie AWD  | 1,844 kg | 4,4 secondes | 580 km    |
      | Standard Plus         | 1,745 kg | 5,6 secondes | 430 km    |
