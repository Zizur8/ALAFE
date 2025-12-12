


export class Cliente {
    constructor({
        idCliente = null,
        idPropietario = null,
        telefono = null,
        nombre = null,
        apellidoPaterno = null,
        apellidoMaterno = null,
        idColonia = null,
        numeroExterior = null,
        calle = null,
        fechaAlta = null,
    } = {})
    {
        this.idCliente = idCliente;
        this.idPropietario = idPropietario;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoMaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idColonia = idColonia;
        this.numeroExterior = numeroExterior;
        this.calle = calle;
        this.fechaAlta = fechaAlta;
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
