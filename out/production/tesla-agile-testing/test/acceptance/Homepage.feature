Feature: Page d'accueil
#  Scenario: Vérification du titre et de la description
#    Given je suis sur la page d'accueil
#    Then le titre doit être "Voitures électriques, énergie solaire et propre | Tesla France"
#    And la description doit être "Tesla accélère la transition mondiale vers une énergie durable en proposant des véhicules électriques, des panneaux solaires et des solutions intégrées d'énergie renouvelable pour les particuliers et les entreprises."
#
#  Scenario Outline: Vérification punchline h1
#    Given je suis sur la page d'accueil
#    Then la punchline principale est "<punchline>"
#    Examples:
#      |punchline                                 |
#      |Model 3                                   |
#      |Model S                                   |
#      |Model X                                   |
#      |Model Y                                   |
#      |Systèmes d'énergie solaire et Powerwalls  |

  Scenario Outline: Vérification menus et liens
    Given je suis sur la page d'accueil
    Then le titre est "<titre>"
    And le lien associé à "<titre>" est "<lien>"
    Examples:
      | titre     | lien                                  |
      | Model S   | https://www.tesla.com/fr_FR/models    |
      | Model 3   | https://www.tesla.com/fr_FR/model3    |
      | Model X   | https://www.tesla.com/fr_FR/modelx    |
      | Model Y   | https://www.tesla.com/fr_FR/modely    |
      | Powerwall | https://www.tesla.com/fr_fr/powerwall |
      | Recharger | https://www.tesla.com/fr_FR/charging  |

#  Scenario Outline: Vérification du menu burger
#    Given je suis sur la page d'accueil
#    When je clique sur le menu burger
#    Then la catégorie est "<categorie>"
#    Examples:
#      | categorie |
#      | Véhicules disponibles |
#      | Véhicules d'occasion |
#      | Reprise |
#      | Cybertruck |
#      | Roadster |
#      | Énergie |
#      | Essais |
#      | Flottes et entreprises |
#      | Nous trouver |
#      | Événements |
#      | Assistance |