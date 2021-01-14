Feature: Evenements Tesla
  Scenario: je vais sur la page evenements
    Given je suis sur la page d'accueil
    When je clique sur le menu burger
    And je clique sur l'onglet "Événements"
    Then je suis sur la page evenements