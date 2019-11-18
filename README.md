# sistema-reservaciones-hoteles
Proyecto SOA para simular un sistema de reservaciones de hoteles, con interacción con microservicios de hoteles y banco


# Documentación

## ValidadorConexiones 


### Entradas

* Email
* FechaReservacionSolicitada
* IDHotel

### Salidas

* 

---


## Validaciones

### Entradas

* 
### Procesos

* 
### Salidas

* 

---

## Flujo de proceso

1. (Verifica disponibilidad) Se recibe solicitud de reservación
2. (Verifica disponibilidad) Se genera ID de reservación
3. (Verifica disponibilidad) Se crea un registro en base de datos (IDReservacion,IDUsuario,IDHotel,FechaReservacion,Monto)
4. (Verifica disponibilidad)Se pide a Hotel.Validaciones que informe si hay habitaciones disponibles en ese hotel 

    1. (Validaciones) Si hay habitaciones disponibles para esa fecha, regresar un código de respuesta 200, un ID de habitación y el costo
    2. (Validaciones) Si no hay, regresar un código de respuesta No Data
5. (RealizarPagos) A) Si hay disponibilidad, realiza el pago invocando a Banco.Pagos, mandando (IDReservacion:Concepto, Email de cliente, idHotel,costo).
   1. (Pagos) Si el cliente tiene suficientes fondos, se realiza el cargo y se devuelve un código de respuesta 200 y el ID.
   2. (Pagos) Si el cliente no tiene suficientes fondos, se devuelve un código de respuesta No acceptable

6. (RealizarPagos) B)

7. (Crear Reservación) Invoca a Reservaciones con (IDreservacion,Email,IDHotel,IDHabitacion,FechaReservacion)
8. (Reservaciones) Verifica que la habitación siga disponible.
   1. (Reservaciones) A) Si sigue disponible, registrarla en base de datos y devolver un código de respuesta 200 y el IDReservacion recibido.
   2. (Reservaciones) B) Si no está disponible, devolver un código de respuesta No Acceptable y el IDReservacion recibido.
9. (Crear factura) En BD se actualiza el estatus de la reservación basado en el código de respuesta recibido. Si se obtiene un código 200, se genera una factura y se registra en la cola de respuesta