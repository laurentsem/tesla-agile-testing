Feature: Evenements Tesla
  Scenario: je vais sur la page evenements
    Given je suis sur la page d'accueil
    When je clique sur le menu
    And je clique sur l'onglet "Événements"
    Then je suis sur la page evenements

  Scenario: Vérification du nombre d'événements
    Given je suis sur la page events
    Then je choisi une ville
    And je vérifie s'il y a 15 evenements