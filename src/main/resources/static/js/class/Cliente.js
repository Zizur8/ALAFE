


export class Cliente {
    constructor(id, nombre,apellidoPaterno,apellidoMaterno,correo,telefono,direccion,colonia,numeroExterior,) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.direccion = direccion;
        this.colonia = colonia;
        this.numeroExterior = numeroExterior;
    }
    get getId() {
        return this.id;
    }
    set setId(id) {
        this.id = id;
    }
    get getNombre() {
        return this.nombre;
    }
    set setNombre(nombre) {
        this.nombre = nombre;
    }
    get getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    set setApellidoPaterno(apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    get getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    set setApellidoMaterno(apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    get getCorreo() {
        return this.correo;
    }
    set setCorreo(correo) {
        this.correo = correo;
    }
    get getTelefono() {
        return this.telefono;
    }
    set setTelefono(telefono) {
        this.telefono = telefono;
    }
    get getDireccion() {
        return this.direccion;
    }
    set setDireccion(direccion) {
        this.direccion = direccion;
    }
    get getColonia() {
        return this.colonia;
    }
    set setColonia(colonia) {
        this.colonia = colonia;
    }
    get getNumeroExterior() {
        return this.numeroExterior;
    }
    set setNumeroExterior(numeroExterior) {
        this.numeroExterior = numeroExterior;
    }
}
