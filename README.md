# Desarrollo Basado en Agentes   2023/2024
## :bulb: Descripción 

Somos el grupo 206 de la asignatura Desarrollo Basado en Agentes, formado por:

**Miembros**

- :bust_in_silhouette: Cano Flores, Julia María 
- :bust_in_silhouette: Muñoz Gómez, Sergio 
- :bust_in_silhouette: Reyes García, Teresa Fernanda
- :bust_in_silhouette: Rincón Otero, Marta
- :bust_in_silhouette: Velázquez Ortuño, Diego

## :file_folder: Estructura del Repositorio

Este repositorio está organizado de la siguiente manera:

### :pushpin: **P2** -- Movimiento de una agente en un mundo bidimensional.

Contamos con tres elementos principales en nuestro proyecto: el agente, el entorno y la vista. Para la clase agente hemos implementado tres comportamientos específicos que son la visión del entorno, la toma de decisión del siguiente movimiento y la acción de moverse según el movimiento calculado anteriormente. Por otro lado, la clase entorno proporciona una representación del entorno en el que el agente realiza sus acciones en el mundo bidimensional. Cuenta con variables como los sensores, que almacenan información sobre las celdas adyacentes al agente, el mapa o las posiciones del agente y el objetivo. Finalmente, hemos implementado una interfaz gráfica que muestra el comportamiento del agente ante los diferentes mapas.

Hemos hecho uso de interfaces para escuchar eventos relacionados con la interfaz gráfica. Contamos con un listener para el entorno y otro para el main. Por otro lado, para la gestión de los posibles movimientos disponibles para el agente (contando con diagonales), en un entorno bidimensional, hemos hecho uso de un enumerado. El directorio de mapas contiene archivos de texto que representan diferentes configuraciones de mapas, desde entornos sin obstáculos hasta entornos con obstáculos complejos.

Dejamos la documentación de la primera iteración en :scroll: [Práctica 2](P2/Documentación/Mmeoria_P2.pdf)

### :envelope: **P3** -- Comunicación entre agentes.
