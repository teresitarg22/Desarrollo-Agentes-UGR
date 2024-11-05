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

Dejamos la documentación de la segunda práctica en :scroll: [Práctica 2](P2/Documentación/Memoria_P2.pdf)

### :envelope: **P3** -- Comunicación entre agentes.

Este proyecto se basa en un sistema multiagente que facilita la interacción entre tres agentes principales: Buscador, Santa Claus y Rudolph, con el objetivo de recuperar los renos perdidos de Santa Claus para la Navidad. Los elementos clave incluyen:

- **Agentes y Comportamientos**: Cada agente tiene un rol específico. Buscador se encarga de la misión de rescate, recorriendo el entorno según las indicaciones de Rudolph. Santa Claus evalúa si Buscador es apto para la misión y espera recibir a los renos con una respuesta de confirmación. Rudolph proporciona las coordenadas de los renos perdidos. Los agentes se comunican mediante tres protocolos de comunicación específicos (ComunicacionBuscador, ComunicacionSantaClaus, ComunicacionRudolph) que implementan el protocolo de FIPA y el paso de mensajes entre agentes.

- **Entorno y Movimiento**: El entorno es un mundo bidimensional donde los agentes realizan sus acciones. Incluye sensores que permiten obtener información sobre las celdas adyacentes, un mapa y las posiciones del agente y del objetivo. La clase Moverse gestiona los movimientos disponibles, incluidos los movimientos en diagonal.

- **Interfaz gráfica**: Se implementó una interfaz que visualiza el comportamiento de los agentes en tiempo real. Existen listeners para registrar eventos en el entorno y en el programa principal.

- **Mapas y Obstáculos**: Los archivos de mapas, en formato de texto, definen distintas configuraciones de entornos, desde áreas despejadas hasta escenarios con obstáculos complejos.

Dejamos la documentación de la tercera práctica en :scroll: [Práctica 3](P3/Documentación/Memoria_P3.pdf)
