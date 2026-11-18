# 🐶 EdadCanina · v3

## Versión v3-mvc

Esta versión corresponde a la transformación de la aplicación EdadCanina a una arquitectura MVC utilizando DataBinding.

### Cómo ejecutar la aplicación

1. Clonar el repositorio.
2. Abrir el proyecto con Android Studio.
3. Seleccionar la rama `v3-mvc`.
4. Sincronizar el proyecto con Gradle.
5. Ejecutar la aplicación en un emulador o dispositivo Android.

La aplicación permite introducir la edad de un perro entre 1 y 30 años y calcular su edad equivalente en años humanos utilizando un factor de 7.

---
## MVC y DataBinding

En esta versión de la aplicación se ha utilizado el patrón MVC para separar las responsabilidades del programa.

- **Modelo:** `EdadCaninaModelo` contiene los datos de la aplicación y toda la lógica relacionada con la validación y el cálculo de la edad.
- **Vista:** el XML de `activity_main.xml` representa la interfaz. DataBinding conecta los datos del modelo con los elementos visuales.
- **Controlador:** `EdadCaninaControlador` recibe las acciones del usuario y ordena al modelo realizar las operaciones correspondientes.

También se ha utilizado **DataBinding** para evitar acceder directamente a las vistas desde `MainActivity`. El modelo es observable y, cuando cambia su estado, la interfaz se actualiza automáticamente.

Además, se ha creado un `BindingAdapter` llamado `AdaptadoresVista` para mostrar los mensajes de error según el estado de validación del modelo.

---
## Explícalo con tus palabras

### 1. ¿Qué diferencia hay entre `@{...}` y `@={...}`?

`@{...}` sirve para mostrar un dato en la interfaz. Es una conexión de una sola dirección, desde el modelo hacia la vista.

`@={...}` permite una conexión en los dos sentidos. En este proyecto se utiliza en el `EditText`, por lo que cuando el usuario escribe, el valor se actualiza en el modelo y, si el modelo cambia, también se actualiza el campo de texto.

### 2. ¿Qué ocurre desde que pulso Calcular hasta que aparece 42?

Cuando pulso el botón **Calcular**, el XML llama al método `calcular()` del controlador. El controlador se encarga de pedir al modelo que haga el cálculo.

El modelo comprueba primero si el valor es válido. Si introduzco `6`, lo convierte a número y lo multiplica por 7, obteniendo `42`. Después cambia su estado a `CORRECTO`.

Como el modelo es observable, DataBinding detecta el cambio y actualiza automáticamente el `TextView`, mostrando el resultado `42`.

### 3. ¿Qué cambiarías para utilizar un factor 6 en vez de 7 y por qué solo habría que modificar un archivo?

Solo tendría que cambiar el valor de la constante `FACTOR_CANINO` dentro de `EdadCaninaModelo`, pasando de `7` a `6`.

Esto es posible porque el cálculo está separado de la interfaz y del controlador. Por eso no tendría que modificar ni el XML ni `MainActivity` ni `EdadCaninaControlador`.

---
## Reto realizado

### Restauración del estado al girar la pantalla

Uno de los retos que he realizado ha sido conseguir que la aplicación mantenga su estado cuando se gira el dispositivo.

Al girar la pantalla, Android puede destruir y volver a crear la `Activity`. Para evitar que se pierda la información, guardo en un `Bundle` el texto introducido y si se había realizado un cálculo correctamente.

Después, cuando se vuelve a crear la `Activity`, recupero esos datos y los vuelvo a cargar en el modelo antes de realizar el binding.

De esta forma, si por ejemplo introduzco `6`, pulso **Calcular** y aparece `42`, al girar el dispositivo el resultado se mantiene.
