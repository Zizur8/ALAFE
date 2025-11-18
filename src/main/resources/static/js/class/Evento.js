

export class Evento {
    constructor(usuario, horarioInicio, horarioFinal, decoracion,costo,especial,gratuito,idUsuarioIngreso
        ,idUsuarioModificacion,fechaUltimaModificacion,cantidadHoraExtra,fechaIngreso,costoHoraExtra,ocupaDosDias,
        notasEvento, idCliente,anticipo,moneda,tasaCambio
    ) {

        this.usuario = usuario;
        this.idCliente = idCliente;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.decoracion = decoracion;
        this.costo = costo;
        this.especial = especial;
        this.gratuito = gratuito;
        this.idUsuarioIngreso = idUsuarioIngreso;
        this.idUsuarioModificacion = idUsuarioModificacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.cantidadHoraExtra = cantidadHoraExtra;
        this.fechaIngreso = fechaIngreso;
        this.costoHoraExtra = costoHoraExtra;
        this.ocupaDosDias = ocupaDosDias;
        this.notasEvento = notasEvento;
        this.anticipo = anticipo;
        this.moneda = moneda;
        this.tasaCambio = tasaCambio;

    }


    get getUsuario() {
        return this.usuario;
    }
    set setUsuario(usuario) {
        this.usuario = usuario;
    }
    get getIdCliente() {
        return this.idCliente;
    }
    set setIdCliente(idCliente) {
        this.idCliente = idCliente;
    }
    get getHorarioInicio() {
        return this.horarioInicio;
    }
    set setHorarioInicio(horarioInicio) {
        this.horarioInicio = horarioInicio;
    }
    get getHorarioFinal() {
        return this.horarioFinal;
    }
    set setHorarioFinal(horarioFinal) {
        this.horarioFinal = horarioFinal;
    }
    get getDecoracion() {
        return this.decoracion;
    }
    set setDecoracion(decoracion) {
        this.decoracion = decoracion;
    }
    get getCosto() {
        return this.costo;
    }
    set setCosto(costo) {
        this.costo = costo;
    }
    get getEspecial() {
        return this.especial;
    }
    set setEspecial(especial) {
        this.especial = especial;
    }
    get getGratuito() {
        return this.gratuito;
    }
    set setGratuito(gratuito) {
        this.gratuito = gratuito;
    }
    get getIdUsuarioIngreso() {
        return this.idUsuarioIngreso;
    }
    set setIdUsuarioIngreso(idUsuarioIngreso) {
        this.idUsuarioIngreso = idUsuarioIngreso;
    }
    get getIdUsuarioModificacion() {
        return this.idUsuarioModificacion;
    }
    set setIdUsuarioModificacion(idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
    get getFechaUltimaModificacion() {
        return this.fechaUltimaModificacion;
    }
    set setFechaUltimaModificacion(fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    get getCantidadHoraExtra() {
        return this.cantidadHoraExtra;
    }
    set setCantidadHoraExtra(cantidadHoraExtra) {
        this.cantidadHoraExtra = cantidadHoraExtra;
    }
    get getFechaIngreso() {
        return this.fechaIngreso;
    }
    set setFechaIngreso(fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    get getCostoHoraExtra() {
        return this.costoHoraExtra;
    }
    set setCostoHoraExtra(costoHoraExtra) {
        this.costoHoraExtra = costoHoraExtra;
    }
    get getOcupaDosDias() {
        return this.ocupaDosDias;
    }
    set setOcupaDosDias(ocupaDosDias) {
        this.ocupaDosDias = ocupaDosDias;
    }
    get getNotasEvento() {
        return this.notasEvento;
    }
    set setNotasEvento(notasEvento) {
        this.notasEvento = notasEvento;
    }
    get getAnticipo() {
        return this.anticipo;
    }
    set setAnticipo(anticipo) {
        this.anticipo = anticipo;
    }
    get getMoneda() {
        return this.moneda;
    }
    set setMoneda(moneda) {
        this.moneda = moneda;
    }
    get getTasaCambio() {
        return this.tasaCambio;
    }
    set setTasaCambio(tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    
}