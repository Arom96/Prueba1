# Prueba1 - Aaron Cruz
#

El presente proyecto trata sobre el mantenimiento CRUD conectado a Firebase con Android Studio.
 
#
# PARTES DEL PROYECTO
#
# Splash Screen
Se creo un fondo de pantalla para que inicie antes de abrir la aplicación 
Para ello se creó una actividad nueva llamada splash_activity en la cual se agrega una proción de código para darle el tiempo de aparición de la pantalla 
#
# Icono de la aplicación 
Para poner un icono a la aplicación se hace clic derecho sobre la carpeta app nuevo Image Asset y se escoge la imagen que queremos de ícono, luego modificamos en el AndroidManifest.xml el apatado de <android:icon="@mipmap/ic_index"> para poner nuestro icono creado.
#
# Creación de la aplicación 
- Primero creamos la interfaz en el apartado de main_activity.xml donde definimos nuestros campos y damos los id de las cajas de texto.
- Se creo un ListView para visualizar los productos creados y seleccionarlos para poder modificarlos posteriormente.
- Depués se creo una clase llamada Producto para crear el objeto con sus atributos.
- En la actividad principal (MainActivity) declaramos nuestras variables para la conexión con la base de datos y cajas de texto.
- Para el apartado de los botones se crea un layout llamado main_menu y creamos nuestros íconos que vienen por defecto en android, dando clic derecho sobre la carpeta rest -> new -> Image Asset.
- Creamos nuestros métodos para crear, editar y eliminar los productos en la actividad principal (MainActivity)
- Finalmente generamos nuestra APK que la podemos encontrar adjunta en el proyecto.


