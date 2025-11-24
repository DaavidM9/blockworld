# BlockWorld -- Proyecto en Java

BlockWorld es un proyecto desarrollado en Java e inspirado en el videojuego Minecraft 
que simula un pequeño mundo, inspirado en la mecánica básica de
juegos sandbox basados en bloques. El sistema genera un mundo
tridimensional, crea un jugador, define su inventario, gestiona
movimiento, comida, salud, bloques, items y estructuras del terreno.

El proyecto incluye:

- Generación procedural del mundo\
- Gestión de bloques, herramientas, armas y comida\
- Inventario y manipulación de ítems\
- Movimiento del jugador con validación de posiciones\
- Sistema de salud y alimentación\
- Excepciones personalizadas\
- Varias clases *Main* para probar funcionalidades

------------------------------------------------------------------------

## Estructura del Proyecto

    /mains
       Main1.java
       Main2_partida1.java
       Main2_RandomWalk.java

    /model
       Block.java
       BlockWorld.java
       Inventory.java
       ItemStack.java
       Location.java
       Material.java
       Player.java
       World.java

    /model/exceptions
       BadInventoryPositionException.java
       BadLocationException.java
       EntityIsDeadException.java
       StackSizeException.java
       WrongMaterialException.java

    test/model
	Block_PreP2Test.java
	BlockWorld_PreP2Test.java
	Inventory_PreP2Test.java
	ItemStack_PreP2Test.java
	Location_PreP1Test.java
	Location_PreP2Test.java
	Material_PreP2Test.java
	Player_PreP2Test.java
	World_PreP1Test.java
	World_PreP2Test.java




------------------------------------------------------------------------

## Generación del Mundo

El mundo (`World`) se genera a partir de:

-   Una **semilla** (`seed`)
-   Un **tamaño** (`size`)
-   Un **nombre**


------------------------------------------------------------------------

## Jugador

La clase `Player` gestiona:

-   Salud y alimentación\
-   Movimiento en los tres ejes (con validaciones)\
-   Inventario e interacción con ítems\
-   Uso de objetos (comida, herramientas, armas)\
-   Detección de muerte

------------------------------------------------------------------------

## 🎒 Inventario e Ítems

El sistema de inventario (`Inventory`):

-   Permite almacenar múltiples `ItemStack`
-   Controla ítems en mano\
-   Valida posiciones al seleccionar objetos

Los objetos (`ItemStack`) incluyen:

-   Herramientas\
-   Armas\
-   Bloques\
-   Comida

Con restricciones como:

-   Tamaño máximo de pila (`MAX_STACK_SIZE = 64`)
-   Herramientas y armas → sólo 1 unidad por stack\
-   Validación de materiales al crear bloques

------------------------------------------------------------------------

## Excepciones Personalizadas

Incluye un conjunto robusto de excepciones para manejar:

-   Tamaños incorrectos de stack\
-   Movimientos o posiciones inválidas\
-   Selección incorrecta en inventario\
-   Material inapropiado para un bloque\
-   Acciones de un jugador muerto

------------------------------------------------------------------------

## Clases Main (Pruebas)

-   **Main1** → testea `Location`, distancias y vecindario\
-   **Main2_partida1** → movimientos simples del jugador\
-   **Main2_RandomWalk** → recorrido aleatorio en el mundo

------------------------------------------------------------------------

## Tests

Los tests verifican de manera integral el funcionamiento del modelo de BlockWorld, comprobando la correcta gestión de mundos, jugadores, posiciones, bloques, materiales, inventarios e ítems. Evalúan tanto el comportamiento normal como los casos límite, asegurando movimientos válidos, uso adecuado de objetos, coherencia en la generación del mundo y el inventario, así como el manejo correcto de excepciones y condiciones inválidas. En conjunto, garantizan la robustez y coherencia del sistema.

