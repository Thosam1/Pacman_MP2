# Description du jeu SuperPacman
Lorsque le joueur lance le jeux, son player (SuperPacmanPlayer) démarre dans un premier niveau très basique qui est sans danger. Dans celui-ci il peut découvrir les éléments du jeux. En revanche, les prochains niveaux contiennent des créatures hostiles qui peuvent lui retirer une vie en cas de contact. 
D'ailleurs, le joueur peut voir les vies qu il reste à son personnage en regardant en haut à gauche de son écran. Le player commence avec 5 vies jaunes(un pacman jaune) qui deviennent grises une par une lorsqu'une vie est perdue. Le joueur a perdu quand les 5 sont grises(une fin de jeux n a pas été implémenté, donc on compte sur l'honeteté du joueur ;) )
Le personnage peut se balader et peut interagir avec son entourage. D'ailleurs, les niveaux n'occupent pas tout l'écran, car nous préférons suivre le joueur. (ceci permet de créer des niveaux très grand. (Evidemment ceci peut etre changé)
Les éléments principaux de ces niveaux sont les suivant:
  *les Cherry: les cerises donnent 200 points et disparaissent lors des interactions avec le joueur. De plus, nous avons fait en sorte que lorsqu'un Cherry est pris, le    joueur va plus vite pendant 5secondes
  *Les Bonus: Lorsque le pacman mange ces espèces de pièces qui tournent sur place, alors il gagne la capacité de manger ses adversaires qui ont instantanément peur de lui
  *Les Keys: Les clés permettent d'ouvrir des Gates. Dans certains cas, il en faut meme deux pour pouvoir ouvrir un Gate
  *Les Diamonds: Chacuns de ces petits points bleu donne 10 points au joueur. (ils sont très abondant) Non seulement, tous les manger peut donner beaucoup de points, mais dans certains niveaux il est indispensable de tous les avoir manger si l'on veut ouvrir des Gates
  *Les lever: Les leviers sont des objets que le pacman ne peut pas manger, néanmoins lorsque que le joueur passe dessus des Gates peuvent s'ouvrir et se fermer
  *les Gates: Ces barrières fonctionnent comme des murs, mais qui peuvent disparaitre. il en existe plusieurs:
    *Celles qui ont besoin d'une ou de deux Key
    *Celles qui peuvent etre ouvertes en passant sur un Lever. Attention, un Lever peut ouvrir et fermer plusieurs Gates
    *Celles qui oscillent, c'est à dire qu'elles s'ouvrent et se ferment après des durées prédéfinies 
  *
  *
  *
  *

## Controles
Le Pacman est controlé par les fleches de votre clavier
La touche:
*UP permet de monter
*DOWN permet de descendre
*LEFT permet de bouger vers la gauche
*RIGHT permet de bouger vers la droite

Le Pacman suit l'orientation commendée par ses touches et avance si il peut rentrer dans la case en face de lui.


## Comment affronter les énemis (les Ghosts)
Ceux-ci cherchent la position du pacman et essayent de le manger(lui foncer dedans). En cas de contact le joueur perd une vie et respawn.
Néanmoins, le joueur a la possibilité de les manger sans perdre de vie lorsqu'il a mangé un bonus. (la durée de ce pouvoir est limitée) Une fois mangé, le joueur gagne beaucoup de points et les Ghosts respawn.

## Gagner des Points
Pour gagner des points il existe 3 moyens:
  *manger un Cherry (200 points)
  *manger un Diamond (10 points)
  *manger un Bonus puis un Ghost(500 points)

## Comment démarrer le jeu
Pour démarrer le jeu, le joueur doit tout simplement lancer le programme. Le pacman apparaitra alors sur le premier niveau.

## Fin du jeu
La fin du jeu n'a pas été spécifiquement implémenté.
Néanmoins, nous éstimons que le joueur a perdu si il perd ses 5 vies, mais qu'il a gagné si il parvient à sortir du dernier niveau.
Evidemment l'objectif est de gagner le plus de points!!!
# BONNE CHANCE
