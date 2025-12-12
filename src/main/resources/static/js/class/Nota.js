
export class Nota {
  constructor(id_evento_nota = null, id_evento = null, nota = null, usuario_ingreso, fechaIngreso = null) {
    this.id_evento = id_evento;
    this.nota = nota;
    this.usuario_ingreso = usuario_ingreso;
    this.id_evento_nota = id_evento_nota;
    this.fechaIngreso = fechaIngreso;
  }



    get getIdEventoNota() {
        return this.id_evento_nota;
    }
    set setIdEventoNota(id_evento_nota) {
        this.id_evento_nota = id_evento_nota;
    }
    get getNota() {
        return this.nota;
    }
    set setNota(nota) {
        this.nota = nota;
    }
    get getIdEvento() {
        return this.id_evento;
    }
    set setIdEvento(id_evento) {
        this.id_evento = id_evento;
    }
    get getUsuarioIngreso() {
        return this.usuario_ingreso;
    }
    set setUsuarioIngreso(usuario_ingreso) {
        this.usuario_ingreso = usuario_ingreso;
    }
    get getFechaIngreso() {
        return this.fechaIngreso;
    }
    set setFechaIngreso(fechaIngreso) {
        this.fechaAlta = fechaIngreso;
    }

}
