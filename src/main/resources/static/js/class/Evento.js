
import EventoPK from './EventoPK.js';

class Evento {
    constructor(EventoPK, horarioInicio, horarioFinal, decoracion,costo,especial,gratuito,idUsuarioIngreso
        ,idUsuarioModificacion,fechaUltimaModificacion,cantidadHoraExtra,fechaIngreso,costoHoraExtra
    ) {
        this.EventoPK = EventoPK;
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

    }
}