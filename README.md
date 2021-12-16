# Projet 5AL 

## Pré-requis

Il est indispensable d'avoir installé en local:

- la version 2.13 du compilateur Scala, [ici](https://scala-lang.org/download/)

- le gestionnaire de build `sbt`, [voir ici](https://www.scala-sbt.org/download.html). En installant `sbt`, le compilateur sera installé aussi.

## Structure du projet

Ceci est le projet de validation du cours d'initiation à la programmation fonctionnelle. Le code source doit être écrit dans le répertoire `./src/main/scala`. Vous pourrez créer autant de package que vous voulez.
Les tests unitaires doivent être écrit dans le répertoire `./src/test/scala`. Pour écrire des tests unitaires, veuillez vous reporter à la section [Tests Unitaires](#tests-unitaires).

## Sujet du projet
### Contexte

La société FunProg a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.

La tondeuse peut être programmée pour parcourir l'intégralité de la surface. La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est divisée en grille pour simplifier la navigation. Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier son orientation. Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante. On assume que les cases directement au Nord et au Sud de la position (x, y) ont pour coordonnées respectivement (x, y+1) et (x, y-1).

Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :
- La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, celles du coin inférieur gauche sont supposées être (0,0)
- La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées. Chaque tondeuse a deux lignes la concernant :
    - la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, séparés par un espace
    - la seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer la pelouse. Les instructions sont une suite de caractères sans espaces.

Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la première a exécuté intégralement sa série d'instructions. Lorsqu'une tondeuse achève une série d'instruction, elle communique sa position et son orientation. Le programme devra être en mesure de marcher avec un nombre non fixe de tondeuses. A la fin de l'exécution du programme, le résultat de l'exécution de toutes les tondeuses sera exportée en format json.

### Objectifs et consignes

L'objectif de ce projet sera de concevoir et écrire un programme `Scala` implémentant la spécification ci-dessus.

De plus le dit porgramme devra respecter les consignes suivantes (`Zéro` sera donné en cas de non-respect de ses consignes):

- il est **INTERDIT** d'utiliser le mot clé `return`
- il est **INTERDIT** d'utiliser le mot-clé `while`
- il est **INTERDIT** d'utiliser le mot-clé `null`
- il est **INTERDIT** d'utiliser les expressions régulières
- tout `if` devra être **exaustif** (c-à-d avec un `else`)
- tout `pattern-matching` devra être **exaustif** (avec un cas par défaut sinon, ou sans warning si pas besoin de cas par défaut - avec `collect` par exemple - )
- la mutabilité explicite (`var`) ou implicite (struture de données mutables du package `scala.collection.mutable` comme `ArrayBuffer`) est **INTERDITE**
- seules les implémentations valides (qui compilent) seront prises en compte et seules celles renvoyant un résultat correct donneront lieu à la totalité des points

Un template de projet `sbt` sera fourni. Celui-ci servira de base pour les devéloppements. Il n'est pas permis de changer les options de compilateurs définis pour ce projet. Lors du parsing des données d'entrées, si les données attendues ne sont pas fournies ou ne sont pas au format attendu, une exception de type `DonneesIncorectesException` (à créer) sera lancée avec un message d'erreur explicite. Il s'agit du seul type d'exception que le programme devra retourner. Veuillez bien à bien limiter les effets de bord aux frontières de votre programme (entrées/sorties) et non au coeur de celui-ci.

### Entrées/sorties et Tests

Pour un exemple où le fichier suivant est fourni en entrée :

```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
```

Les 2 tondeuses devront respectivement se terminer avec les positions suivantes:

```
1 3 N
5 1 E
```

On attend le résultat suivant (position finale des tondeuses) à la fin de l'exécution du programme:

```json
{
    "limite": {
        "x": 5,
        "y": 5
    },
    "tondeuses": [
        {
            "debut": {
                "point": {
                    "x": 1,
                    "y": 2
                },
                "direction": "N"
            },
            "instructions": ["G","A","G","A","G","A","G","A","A"],
            "fin": {
                "point": {
                    "x": 1,
                    "y": 3
                },
                "direction": "N"
            }
        },
        {
            "debut": {
                "point": {
                    "x": 3,
                    "y": 3
                },
                "direction": "E"
            },
            "instructions": ["A","A","D","A","A","D","A","D","D","A"],
            "fin": {
                "point": {
                    "x": 5,
                    "y": 1
                },
                "direction": "E"
            }
        }
    ]
}
```

### Bonus

Cette section _bonus_ est **OBLIGATOIRE** pour les groupes de 3 personnes. Ceci est un complément à la version simple.

Pour la sortie, en plus `json` et du `csv`, le programme devra en plus exporté sa sortie en `yaml` et le chemin de la sortie devra être configurable. Vous aurez aussi à déterminer la hiérarchie de `typeclasses` adéquates. L'utilisation de `typeclasses` est aussi **OBLIGATOIRE**. Voici un exemple de la sortie pour l'exemple (de la section [Entrées/sorties et Tests](#entrées/sorties-et-tests)):

```yaml
limite:
  x: 5
  y: 5
tondeuses:
- debut:
    point:
      x: 1
      y: 2
    direction: N
  instructions:
  - G
  - A
  - G
  - A
  - G
  - A
  - G
  - A
  - A
  fin:
    point:
      x: 1
      y: 3
    direction: N
- debut:
    point:
      x: 3
      y: 3
    direction: E
  instructions:
  - A
  - A
  - D
  - A
  - A
  - D
  - A
  - D
  - D
  - A
  fin:
    point:
      x: 5
      y: 1
    direction: E
```

---
## Guide de survie avec sbt

Ce projet est un application Scala standalone. Il est géré par `sbt`, le build tool Scala. Sa documentation est disponible [ici](https://www.scala-sbt.org/1.x/docs/).

Nous allons lister ici une liste de commandes utiles avec `sbt`:

- `sbt`: cette commande lance un invite de commande interactif

- `run` (ou `sbt run` hors de l'invite de commande): lance la classe `Main` du projet `sbt`

- `compile` (ou `sbt compile` hors de l'invite de commande): lance la compilation de l'ensemble du projet `sbt` (compile toutes les classes)

- `console` (`sbt console` hors de l'invite de commande): lance un REPL interactif Scala. Les dépendances du projet `sbt` seront disponibles et pourront être importés.

## Manipulation de fichiers

Nous allons voir ici quelques commandes pour vous aider avec la manipulation de fichiers en `Scala`. 

Pour lire un fichier nous pouvons le faire comme suit (en utilisant la lib [better-files](https://github.com/pathikrit/better-files)):

```scala
import better.files._

val f = File("/User/johndoe/Documents") // using constructor

// on va récupérer toutes les lignes du fichier
f.lines.toList

// si on veut récupérer tout le contenu du fichier en String
f.contentAsString
```

Pour écrire dans un fichier, nous pouvons le faire ainsi:

```scala
import better.files._

val f = File("/User/johndoe/Documents") // using constructor

// pour ajouter du contenu dans un fichier ligne par ligne
f.createIfNotExists()
  .appendLine() // on rajoute une ligne vide
  .appendLines("My name is", "Inigo Montoya") // on ajoute 2 nouvelles lignes

// pour écraser le contenu du fichier
f.createIfNotExists().overwrite("hello")
```

## Tests unitaires

Il est possible de lancer tous les tests du projets avec la commande: `sbt test` (ou `test` si on est dans l'invite de commande `sbt`).

Pour créer une classe de test, il suffit de créer une classe étendant `org.scalatest.funsuite.AnyFunSuite`:

```scala

import org.scalatest.funsuite.AnyFunSuite

class HelloSpec extends AnyFunSuite {}
```

Les tests devant être lancés doivent être placés dans le corps de la classe. Pour créer un test, il suffit d'appeler `test` en lui passant un nom de test et le code de test à effectuer comme ceci:

```scala
import org.scalatest.funsuite.AnyFunSuite

class HelloSpec extends AnyFunSuite {
  test("The Hello object should say hello") {
    assert(Hello.greeting === "hello")
  }
}
```

Le test sera lancé dès lorsqu'on lancera la commande `test`:

```scala
sbt:funprog-AL> test
[info] Formatting 1 Scala sources...
[info] compiling 1 Scala source to ../projet/funprog-al/target/scala-2.13/test-classes ...
[info] HelloSpec:
[info] - The Hello object should say hello
[info] Run completed in 251 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 1 s, completed 14 nov. 2021 à 14:46:48
```

Une classe de test d'exemple vous est fourni dans `./src/test/example/HelloSpec.scala`.

## Lecture des fichiers de conf

La librairie [config](https://github.com/lightbend/config) a été rajouté au projet. Elle permet de lire les fichiers de configuration (au format `.conf`).
Un fichier de configuration a éta ajouté au projet (voir le fichier `./src/main/resources/application.conf`).

Voici un exemple d'utilisation de l'api `config` (tiré de la documentation officielle).

Pour le fichier `application.conf` suivant:

```conf
foo {
  bar = 1
}

foo1.baz = "some texte"
foo1.baz = ${?FOO1_BAZ} # variable d'environnement pour sucharger la conf
```

La lecture du fichier de conf se fera comme suit:

```scala
import com.typesafe.config.{Config, ConfigFactory}

// Pour charger la configuration. `ConfigFactory#load` va chercher et lire le fichier `application.conf`.
val conf: Config = ConfigFactory.load()

// Une fois le fichier de conf chargé, on peut récupéré une valeur par sa clé (ex: `foo.bar`).
val bar1: Int = conf.getInt("foo.bar")
val foo: Config = conf.getConfig("foo")
val bar2: Int = foo.getInt("bar")
val baz: String = conf.getString("foo1.baz")
```
