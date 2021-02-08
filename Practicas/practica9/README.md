Introducción a Ciencias de la Computación
=========================================

Práctica 9: Ordenamientos y búsquedas
-------------------------------------

### Fecha de entrega: martes 19  de enero, 2021

Deben agregarle una interfaz gráfica con
[JavaFX](https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm) a
su base de datos de estudiantes.

![Administrador de Estudiantes](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/curso/-/wikis/uploads/2512a978c82836fe489c4bba1d997439/administrador-de-estudiantes.png)

Esto implicará cambiar las propiedades de
[Estudiante](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/Estudiante.java),
a propiedades de JavaFX para poder agregar escuchas que reaccionen cuando
cambien su valor.

De la misma manera, deben modificar
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java)
para que se le puedan agregar escuchas instancias de
[EscuchaBaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/EscuchaBaseDeDatos.java),
que reaccionen cuando la base de datos sea modificada; los escuchas reaccionarán
a eventos determinados en la enumeración
[EventoBaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/EventoBaseDeDatos.java).

Una vez que hayan terminado sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas sus pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica9](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/Practica9.java)
al hacer:

```
$ mvn install
...
$ mvn -q exec:java \
      -f pom.xml   \
      -Dexec.mainClass=mx.unam.ciencias.icc.Practica9
```

El cómo se ejecuta la práctica cambió de las prácticas anteriores, ya que Java
11 utiliza módulos y JavaFX es uno de ellos. Se puede crear un "Jar gordo" que
incluya los módulos necesarios de JavaFX; pero lo más sencillo es utilizar Maven
para ejecutar el programa. Dado lo elaborado de la línea de comandos necesaria
para ejecutar el programa, se incluye también un *script* para hacerlo más
sencillo:

```
$ ./bin/practica9
```

Los únicos archivos que deben modificar son:

* `Arreglos.java`,
* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java` y
* `Lista.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica9.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
9](https://aztlan.fciencias.unam.mx/~canek/2021-1-icc/practica9/apidocs/index.html)
