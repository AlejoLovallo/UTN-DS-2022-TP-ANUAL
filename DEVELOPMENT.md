# Desarrollo

### Gitflow 

_Normal workflow_

* git pull
* git checkout develop
* git checkout -b nombre_rama
* git add -A
* git commit -m "feat(commit_title): commit_description"
* git push

_Traerse cambios de develop:_

* git checkout develop
* git pull
* git checkout rama_en_la_que_esten_trabajando
* git merge develop
* git push

### Dependencias

* java: >=8. jdk _
* jUnit: Para test.
* apachePoi: Libreria usada para la carga de archivos excel.
* retrofit2: Libreria usada para la comunicacion de la api de la catedra

### Compilacion

```
```

### Carga de archivos de actividades

* Deberán ser en Excel y es importante salvarlos en formato .xls y NO .xlsx .


### Tests

```
```

**********

### Entrega 2

Correciones entrega 1 

* La clase contraseñia parece un sobrediseño ---> SOLUCIONADO
* ENUM adentro de otro ENUM? ---> SOLUCIONADO 
* Los wireframes tienen mucho detalle y colores --> SIN ACCION.
* Por ahora no incluyan "métodos de interfaz" como Admin ---> ???
* En Miembro hay un Enum modelado distinto que otros Enum ----> REVISAR
* Porqué hay un repositorio de sectores, pero no otros repositorios (Espacio por ejemplo)? ---> AGREGAMOS TODOS LOS REPOSITORIOS??
                                                                                                REVISAR
* El sector no está relacionado con la organización? Hay sectores estándares? ----> ???
* Las flechas de la herencia están mal -----> REVISAR DIAGRAMA
* La clase BiciPie está vacía y no hereda nada. ¿Qué hace esa clase? ---> SOLUCIONAR

Correciones para todos

* Ojo con el uso de "línea de colectivo" como entero. Tengan en cuenta que una línea puede tener ramales. ¿el modelo soporta esos ramales?
* Tengan en cuenta que un trayecto puede ser realizado de manera compartida (al final, también ese puede ser un objetivo para reducir huella de carbono) ---> SOLUCIONADO
* Qué pasa si un miembro trabaja para 2 organizaciones con trayectos diferentes? El modelo lo soporta? ---> SI
* En el modelo poner las clases dentro de paquetes (mañana lo vemos) -----> ENTIENDO QUE ESTA HECHO ESTO
* Agregar tipos de datos en el DER. Considerar los tipos de datos preferentemente según el motor de base de datos que van a utilizar. ---> REVISAR
* Les vamos a pedir documentar en las decisiones o en algún apartado cómo resolvieron el algoritmo de limite o incremento de tiempo de respuesta --> HACERLO
* Les vamos a pedir documentar cómo es el proceso de validación (podrían usar Diagrama de Secuencia, que veremos mañana) ----> HACERLO (TOMI)


Diagramas 
    * Falta actualizar diagrama de clases con clases interfaz y gestoras
    * Actualizar DER en caso que sea necesario
    * Hacer diagramas de secuencia

Codigo
    * Terminar api y excel (ALEJO) --> LISTO
    * Agregar lo de la clase Persona y romper la relacion de miembors (ALEJO) --> LISTO
    * Fijarse en que quedo lo del calculo de distancias en la parte de organizacion de seba
    * Fijarse en que quedo lo de transporte --> SEBA --> ENTIENDO QUE LISTO
    * Fijarse que hacer con la clase BICI/PIE
    * Armar clases gestoras e interfaz
    * Tests Sabrina










