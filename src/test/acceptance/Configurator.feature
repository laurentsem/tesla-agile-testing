Feature: Configurateur Model S
#  Scenario: Vérification du titre et de la description
#    Given je suis sur la page du modèle Tesla Model S
#    When je clique sur le bouton commander
#    Then j'accède à la page de configuration Tesla Model S
#
#  Scenario: Vérification du prix LOA par défaut
#    Given je suis sur la page de configuration Tesla Model S
#    Then le prix affiché par défaut est de "768" euros par mois
#
#  Scenario Outline: Vérification des prix de différentes options
#    Given je suis sur la page de configuration Tesla Model S
#    When je clique sur le bouton "<modele>"
#    Then le prix LOA est "<prixLOA>", "<carburantEconomie>" d'économies de carburant
#    When je clique sur Voir détails
#    Then le montant total est de "<montantTotal>"
#    Examples:
#      | modele                | prixLOA | carburantEconomie | montantTotal |
#      | Grande Autonomie Plus | 768     | 108               | 94 841       |
#      | Performance           | 969     | 108               | 114 052      |
#
#  Scenario: Vérification hausse de prix avec Pilote auto
#    Given je suis sur la page de configuration Tesla Model S
#    # When je clique sur l'onglet Pilotage Automatique
#    When je clique sur le bouton Ajouter cette option
#    Then le prix affiché par défaut "768" euros augmente de "89" euros

  Scenario: Je souhaite connaitre les localisations de vente
    Given je suis sur la page de configuration Tesla Model S
    When je clique sur le logo Tesla en haut à gauche
    Then je suis sur la page d'accueil
    When je clique sur "Localisations"
    Then j'arrive sur la page "https://www.tesla.com/fr_fr/findus/list"
