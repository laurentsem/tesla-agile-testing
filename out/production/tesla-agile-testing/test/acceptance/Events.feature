Feature: Evenements Tesla
#  Scenario: je vais sur la page evenements
#    Given je suis sur la page d'accueil
#    When je clique sur le menu
#    And je clique sur l'onglet "Événements"
#    Then je suis sur la page evenements
#
#  Scenario: Vérification du nombre d'événements
#    Given je suis sur la page events
#    Then je choisi une ville "Hong kong"
#    And je vérifie s'il y a 15 evenements

#  Scenario: Vérification liens
#    Given je suis sur la page events
#    Then il y a un lien Voir tous les événements
#    Then il y a un lien Afficher plus

#  Scenario Outline: Vérification champs formulaire
#    Given je suis sur la page events
#    Then il y a le champs "<champs>"
#    And il y a un bouton "Suivant"
#    Examples:
#      | champs                  |
#      | Prénom                  |
#      | Nom                     |
#      | E-mail                  |
#      | Téléphone               |
#      | Region                  |
#      | Code postal             |
#      | Recevoir les News Tesla |

#  Scenario Outline:
#    Given je suis sur la page events
#        And je saisi le prénom "<prenom>"
#        And je saisi le nom "<nom>"
#        And je saisi le téléphone "<telephone>"
#        And je saisi le region "<region>"
#        And je saisi le code postal "<code_postal>"
#    When je clique sur suivant
#    Then un message rouge apparaît avec le mot "obligatoire"
#    Examples:
#      | prenom | nom   | telephone  | region | code_postal |
#      | Sarah  | Hayat | 770139965  | France | 95270       |

#    Scenario: Recheche d'événements
#      Given je suis sur la page events
#      Then je choisi une ville "Londres"
#      Then je vérifie la ville du premier événement affiché est "Royaume-Uni"

    Scenario: Evénement au japon
      Given je suis sur la page events
      Then je choisi une ville "Japon"
      When je clique sur le bouton d'inscription
      Then je vérifie que je suis rédirigé sur le lien "https://auth.tesla.com/"


