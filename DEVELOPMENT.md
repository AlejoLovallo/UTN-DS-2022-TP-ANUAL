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

******

Problemas de llevar el der a la practiva

clase direccion: El Der tiene campos de menos

clase Espacio: La tenemos codeada como abstracta , creo que en hibernate no se va a instanciar nunca.

clase FactorEmision: id_usuario_update ???

clase Organizacion: El archivo de mediciones y el num dias por semana no lo estamos guardando, es necesario??

La organizacion tiene un Contacto y en el DER no esta eso. 
Falta tambien actividades








