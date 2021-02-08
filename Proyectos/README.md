Introducción a Ciencias de la Computación
=========================================

Proyectos
---------

* [Proyecto 1](Proyectos/Proyecto 1) (fecha de entrega: 4 de diciembre de 2020)
* [Proyecto 2](Proyectos/Proyecto 2) (fecha de entrega: 15 de enero de 2021)
* [Proyecto 3](Proyectos/Proyecto 3) (fecha de entrega: 5 de febrero de 2021)

Formato de entrega
------------------

Los proyectos deben entregarse en un archivo `proyectoX.tar.gz` que contenga el
directorio `proyectoX` como raíz, dentro del cual esté un archivo `pom.xml` que
permita compilar el código, correr pruebas unitarias en el mismo (si aplica) y
generar un archivo Jar llamado `proyectoX.jar` al hacer:

```
$ mvn compile # compila el código
$ mvn test    # corre las pruebas unitarias (opcional)
$ mvn install # genera el archivo proyectoX.jar en el subdirectorio target
```

Con el archivo `proyectoX.jar` se ejecutará el programa al invocar:

```
java -jar target/proyectoX.jar
```

**Obviamente** `X` denota el número del proyecto.

El archivo `proyectoX.tar.gz` deben enviarlo adjuntado en un correo electrónico
dirigido **al profesor**; el correo debe tener el asunto: `“ICCProyecto X
Apellido Paterno Apellido Materno Nombre(s)”`. Nótese que `“ICCProyecto”` *no
contiene espacios*.

Los proyectos deben cumplir todos estos requisitos o no serán aceptados y se
evaluarán con cero.
