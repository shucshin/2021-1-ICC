Introducción a Ciencias de la Computación
=========================================

Práctica 7: Iteradores
----------------------

### Fecha de entrega: martes 8 de diciembre, 2020

Deben convertir su clase
[Lista](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica7/blob/master/src/main/java/mx/unam/ciencias/icc/Lista.java)
a que sea iterable, lo que implicará cambiar también
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica7/blob/master/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java).

Una vez que hayan terminado sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas sus pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica7](https://aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica7/blob/master/src/main/java/mx/unam/ciencias/icc/Practica7.java)
al hacer:

```
$ mvn install
...
$ java -jar target/practica7.jar -g archivo.db # guarda la base de datos
...
$ java -jar target/practica7.jar -c archivo.db # carga la base de datos
```

Los únicos archivos que deben modificar son:

* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java` y
* `Lista.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2021-1-icc/practica7.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
7](https://aztlan.fciencias.unam.mx/~canek/2021-1-icc/practica7/apidocs/index.html)
