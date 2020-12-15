# Description du jeu SuperPacman
Lorsque le joueur lance le jeux, son player (SuperPacmanPlayer) démarre dans un premier niveau très basique qui est sans danger. Dans celui-ci il peut découvrir les éléments du jeux. En revanche, les prochains niveaux contiennent des créatures hostiles qui peuvent lui retirer une vie en cas de contact. 
D'ailleurs, le joueur peut voir les vies qu il reste à son personnage en regardant en haut à gauche de son  et il peut voir sont score en à droite de ses vies. Le player commence avec 5 vies jaunes(un pacman jaune) qui deviennent grises une par une lorsqu'une vie est perdue. Le joueur a perdu quand les 5 sont grises(une fin de jeux n a pas été implémenté, donc on compte sur l'honeteté du joueur ;) )
Le personnage peut se balader et peut interagir avec son entourage. D'ailleurs, les niveaux n'occupent pas tout l'écran, car nous préférons suivre le joueur. Ceci permet de créer des niveaux très grand sans avoir à rendre les cellules très petites. (Evidemment ceci peut etre changé)
Les éléments principaux de ces niveaux sont les suivant:
### les Cherry: 
les cerises donnent 200 points et disparaissent lors des interactions avec le joueur. De plus, nous avons fait en sorte que lorsqu'un Cherry est pris, le    joueur va plus vite pendant 5secondes
### Les Bonus: 
Lorsque le pacman mange ces espèces de pièces qui tournent sur place, alors il gagne la capacité de manger ses adversaires qui ont instantanément peur de lui
### Les Keys: 
Les clés permettent d'ouvrir des Gates. Dans certains cas, il en faut meme deux pour pouvoir ouvrir une Gate
### Les Diamonds: 
Chacuns de ces petits points bleu donne 10 points au joueur. (ils sont très abondant) Non seulement, tous les manger peut donner beaucoup de points, mais dans certains niveaux il est indispensable de tous les avoir manger si l'on veut ouvrir des Gates
### Les lever: 
Les leviers sont des objets que le pacman ne peut pas manger, néanmoins lorsque que le joueur passe dessus des Gates peuvent s'ouvrir et se fermer
### Les Gates: 
Ces barrières fonctionnent comme des murs, mais qui peuvent disparaitre. il en existe plusieurs:
    *Celles qui ont besoin d'une ou de deux Key   
    *Celles qui peuvent etre ouvertes en passant sur un Lever. Attention, un Lever peut ouvrir et fermer plusieurs Gates   
    *Celles qui oscillent, c'est à dire qu'elles s'ouvrent et se ferment après des durées prédéfinies   
    *Celles qui s'ouvrent lorsque tous les diamants ont été collecté
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

## Niveau0
Ce premier niveau est très basique et sert à faire découvrir les acteurs qui ne sont pas hostile au joueur. Ainsi, il contient un bonus, des diamants, un cherry, une clé et plusieurs gates. Lorsque vous prendrez la clé, vous remarquerez qu'une des gate disparaitra. Il s'agit là de la méthode basique pour ouvrir des gates. Sinon, une des gates ne pourra que s'ouvrir sous condition d'avoir collecté tous les diamants, sauf que pour cela il faudra passer une Gate grace à un troisième moyen. Il faudra passer sur le Lever et instantanément, la Gate disparaitra et une autre Gate apparaitra derrière vous. Finalement, ce niveau possède un dernier type de Gate qui s'ouvre et se referme tout seul grace à un timer.
## Niveau1
Attention, ce niveau est plein de créatures hostiles! Vous remarquerez que deux Gates vous bloquent la sortie, mais qu'il n'y a pas de clés. Ainsi, il faudra collecter tous les diamants du niveau et ensuite elles s'ouvreront. N'oubliez pas de manger des cerises si vous voulez pouvoir vous échapper le plus rapidement possible.
## Niveau2
Ce dernier niveau implémente toutes les Gates que vous avez découvert dans le premier niveau(sauf cel. Attention à ne pas vous faire piéger par des Gates qui apparaissent et disparaissent! Pour réussir à passer ce niveau, il faudra collecter toutes les clés. Certaines ne seront pas atteignable à moins d'avoir ouvert les Gates qui les protègent. Et finalement, après avoir pris toutes les clés, les portes s'ouvriront.

## Comment démarrer le jeu
Pour démarrer le jeu, le joueur doit tout simplement lancer le programme. Le pacman apparaitra alors sur le premier niveau.

## Fin du jeu
La fin du jeu n'a pas été spécifiquement implémenté.
Néanmoins, nous éstimons que le joueur a perdu si il perd ses 5 vies, mais qu'il a gagné si il parvient à sortir du dernier niveau.
Evidemment l'objectif est de gagner le plus de points!!!
# BONNE CHANCE
