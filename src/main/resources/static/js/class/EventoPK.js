

export class EventoPK {
    constructor(idEvento, idUsuario) {
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
    }

    get getIdEvento() {
        return this.idEvento;
    }
    get getIdUsuario() {
        return this.idUsuario;
    }

}