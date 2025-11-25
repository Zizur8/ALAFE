import { Evento } from "../class/Evento.js";
import { Cliente } from "../class/Cliente.js";
export class FormularioMain {
  constructor(formId = "form-evento") {
    this.fechaSelected = null;
    this.form = document.getElementById("form-evento");
    this.estadoFormulario = "";
    this.cliente = null;
    this.evento = null;
    this.inputs = {
      horarioInicio: this.form.querySelector("#horarioInicio"),
      horarioFin: this.form.querySelector("#horarioFin"),
      ocupaDosDias: this.form.querySelector("#ocupaDosDias"),
      decorar: this.form.querySelector("#decorar"),
      horarioDecoracion: this.form.querySelector("#horarioDecoracion"),
      esEspecial: this.form.querySelector("#esEspecial"),
      cantidadHorasExtras: this.form.querySelector("#cantidadHorasExtras"),
      costoPorHoraExtra: this.form.querySelector("#costoPorHoraExtra"),
      costoEvento: this.form.querySelector("#costoEvento"),
      anticipo: this.form.querySelector("#anticipo"),
      moneda: this.form.querySelector("#moneda"),
      tasaCambio: this.form.querySelector("#tasaCambio"),
      telefonoCliente: this.form.querySelector("#telefonoCliente"),
      correoCliente: this.form.querySelector("#correoCliente"),
      direccionCliente: this.form.querySelector("#direccionCliente"),
      coloniaCliente: this.form.querySelector("#coloniaCliente"),
      numeroExterior: this.form.querySelector("#numeroExterior"),
      nombreCliente: this.form.querySelector("#nombreCliente"),
      apellidoPaternoCliente: this.form.querySelector(
        "#apellidoPaternoCliente"
      ),
      apellidoMaternoCliente: this.form.querySelector(
        "#apellidoMaternoCliente"
      ),
      notaEvento: this.form.querySelector("#notaEvento"),
      costoTotal: this.form.querySelector("#costoTotal"),
      saldoOperacion: this.form.querySelector("#saldoOperacion"),
    };
    this.buttons = {
      mostrarListadoMovimientos: this.form.querySelector(
        "#listado-movimientos-evento"
      ),
      submitEvento: this.form.querySelector("#submit-evento"),
    };
    this.tags = {
      miniDecorar: this.form.querySelector("#mini-decorar"),
      miniSaldo: this.form.querySelector("#mini-saldo"),
      miniHorasExtras: this.form.querySelector("#mini-horas-extras"),
      miniTieneNotas: this.form.querySelector("#mini-tiene-notas"),
      miniStatusLiquidado: this.form.querySelector("#mini-status-liquidado"),
    };
    this.labels = {
      anticipos: this.form.querySelector("#lblAnticipos"),
      horarioDecoracion: this.form.querySelector("#lblHorarioDecoracion"),
      tasaCambio: this.form.querySelector("#lblTasaCambio"),
      moneda: this.form.querySelector("#lblMoneda"),
      costoPorHoraExtra: this.form.querySelector("#lblCostoPorHoraExtra"),
      cantidadPorHorasExtras: this.form.querySelector(
        "#lblCantidadHorasExtras"
      ),
    };

    this.initListeners();

  }

  initListeners() {
    this.inputs.decorar.addEventListener("change", () => this.actualizarHorarioDecoracion());
    this.inputs.anticipo.addEventListener("change", () => this.actualizarComponentesMonetarios());
    this.inputs.tasaCambio.addEventListener("change", () => this.actualizarComponentesMonetarios());
    this.inputs.costoEvento.addEventListener("change", () => this.actualizarComponentesMonetarios());
    this.inputs.cantidadHorasExtras.addEventListener("change", () => this.calcularCostoTotal());
    this.inputs.costoPorHoraExtra.addEventListener("change", () => this.calcularCostoTotal());
    this.inputs.moneda.addEventListener("change", () => this.actualizarComponentesMonetarios());
  }

  limpiarFormulario() {
    this.inputs.decorar.checked = true;
    Object.values(this.inputs).forEach((input) => {
      if (input.type === "checkbox") {
        input.checked = false;
      } else if (input.tagName === "SELECT") {
        input.selectedIndex = 0;
      } else {
        input.value = "";
      }
      input.style.display = "";
    });
    this.inputs.tasaCambio.value = 17.0;
    this.actualizarHorarioDecoracion();
    this.actualizarComponentesMonetarios();
  }

  llenarFormulario(evento, cliente) {
    console.log("evento recibido en llenarFormulario:", evento);
    this.inputs.horarioInicio.value = this.formatearHora(evento?.horarioInicio);
    this.inputs.horarioFin.value = this.formatearHora(evento?.horarioFin);
    this.inputs.cantidadHorasExtras.value = Number(
      evento?.cantidadHoraExtras ?? 0
    );
    this.inputs.costoEvento.value = Number(evento?.costo ?? 0);
    this.inputs.anticipo.value = Number(evento?.anticipo ?? 0);
    this.inputs.decorar.checked = !!evento?.decoracion;
    this.inputs.esEspecial.checked = !!evento?.especial;
    this.inputs.telefonoCliente.value = cliente?.telefono ?? "";
    this.inputs.correoCliente.value = cliente?.correo ?? "";
    this.inputs.direccionCliente.value = cliente?.direccion ?? "";
    this.inputs.coloniaCliente.value = cliente?.colonia ?? "";
    this.inputs.numeroExterior.value = cliente?.numeroExterior ?? "";
    this.inputs.nombreCliente.value = cliente?.nombre ?? "";
    this.inputs.apellidoPaternoCliente.value = cliente?.apellidoPaterno ?? "";
    this.inputs.apellidoMaternoCliente.value = cliente?.apellidoMaterno ?? "";
    this.inputs.notaEvento.value = evento?.notasEvento ?? "";
    this.inputs.costoPorHoraExtra.value = evento?.costoHoraExtra ?? "";
    
    this.actualizarComponentesAnticipos();
    this.calcularCostoTotal();
    this.actualizarLabelMovimiento();
    this.actualizarComponentesHorasExtras();
    // this.mostrarBotonListadoMovimientos();
  }

  llenarFormularioConDatosCliente(cliente) {

    console.log("Limpiar datos clientes");
    this.inputs.telefonoCliente.value = cliente?.telefono ?? "";
    this.inputs.correoCliente.value = cliente?.correo ?? "";
    this.inputs.direccionCliente.value = cliente?.direccion ?? "";
    this.inputs.coloniaCliente.value = cliente?.colonia ?? 0;
    this.inputs.numeroExterior.value = cliente?.numeroExterior ?? "";
    this.inputs.nombreCliente.value = cliente?.nombre ?? "";
    this.inputs.apellidoPaternoCliente.value = cliente?.apellidoPaterno ?? "";
    this.inputs.apellidoMaternoCliente.value = cliente?.apellidoMaterno ?? "";
  }

  mostrarBotonListadoMovimientos() {
    console.log(this.estadoFormulario);
    this.buttons.mostrarListadoMovimientos.style.display =
      this.estadoFormulario == "edit" ? "inline-block" : "none";
  }

  actualizarLabelMovimiento() {
    if (this.estadoFormulario == "edit") {
      console.log("ediiiit");
      this.labels.anticipos.textContent = "Abono/Anticipo";
    } else {
      console.log("elseee");
      this.labels.anticipos.textContent = "Anticipo";
    }
  }

  actualizarBotonSubmit() {
    console.log("Estado formlario:" + this.estadoFormulario);
    this.buttons.submitEvento.innerHTML =
      this.estadoFormulario == "create" ? "Agendar Evento" : "Guardar Evento";
  }

  actualizarTags() {
    this.tags.miniHorasExtras.innerHTML =
      "H. Extras: " + this.inputs.cantidadHorasExtras.value.trim();
    this.tags.miniHorasExtras.style.display =
      this.inputs.cantidadHorasExtras.value > 0 ? "inline" : "none";
    this.tags.miniTieneNotas.style.display =
      this.inputs.notaEvento.value !== "" ? "inline" : "none";
  }

  actualizarHorarioDecoracion() {
    this.inputs.horarioDecoracion.style.display = this.inputs.decorar.checked
      ? "inline"
      : "none";
    this.labels.horarioDecoracion.style.display = this.inputs.decorar.checked
      ? "inline"
      : "none";
  }

  actualizarComponentesAnticipos() {
    this.labels.anticipos.style.display =
      this.inputs.costoEvento.value > 0 ? "inline" : "none";
    this.inputs.anticipo.style.display =
      this.inputs.costoEvento.value > 0 ? "inline" : "none";
  }

  actualizarComponentesHorasExtras() {
    this.labels.costoPorHoraExtra.style.display =
      this.inputs.cantidadHorasExtras.value > 0 ? "inline" : "none";
    this.inputs.costoPorHoraExtra.style.display =
      this.inputs.cantidadHorasExtras.value > 0 ? "inline" : "none";
    this.calcularCostoTotal();
  }

  actualizarComponentesMonetarios() {
    const costoEventoValido =
      Number(this.inputs.costoEvento.value) > 0 ? true : false;
    const anticipoCampo = Number(this.inputs.anticipo.value) > 0 ? true : false;
    console.log(costoEventoValido);
    const indexMonedaActual = this.inputs.moneda.selectedIndex;

    if (indexMonedaActual == 0) {
      this.inputs.tasaCambio.value = 1;
    } else {
      this.inputs.tasaCambio.value = 17.0;
    }

    this.labels.anticipos.style.display = costoEventoValido ? "inline" : "none";
    this.inputs.anticipo.style.display = costoEventoValido ? "inline" : "none";
    this.labels.moneda.style.display = anticipoCampo ? "inline" : "none";
    this.inputs.moneda.style.display = anticipoCampo ? "inline" : "none";

    this.labels.tasaCambio.style.display =
      indexMonedaActual == 0 ? "none" : "inline";
    this.inputs.tasaCambio.style.display =
      indexMonedaActual == 0 ? "none" : "inline";

    this.calcularCostoTotal();
  }

  calcularCostoTotal() {
    this.inputs.costoTotal.value = "";
    console.log(this.inputs.costoEvento.value);
    console.log(this.inputs.costoPorHoraExtra.value);
    console.log(this.inputs.cantidadHorasExtras.value);
    this.inputs.costoTotal.value =
      Number(this.inputs.costoEvento.value) +
      Number(this.inputs.costoPorHoraExtra.value) *
      Number(this.inputs.cantidadHorasExtras.value);
    this.calcularSaldo();
  }

  calcularSaldo() {
    this.inputs.saldoOperacion.value = "";
    this.inputs.saldoOperacion.value =
      Number(this.inputs.costoTotal.value) - Number(this.inputs.anticipo.value);
  }

  obtenerEventoParaCrear() {
    const horaInicio = this.inputs.horarioInicio.value;
    const horaFin = this.inputs.horarioFin.value;
    console.log(this.fechaSelected);
    const horarioInicio =
      fecha && horaInicio ? `${fecha}T${horaInicio}:00` : null;
    const horarioFin = fecha && horaFin ? `${fecha}T${horaFin}:00` : null;
    // const horarioInicio = (this.fechaSelected && horaInicio) ? new Date(`${this.fechaSelected}T${horaInicio}:00`) : null;
    // const horarioFin = (this.fechaSelected && horaFin) ? new Date(`${this.fechaSelected}T${horaFin}:00`) : null;
    const horarioDecoracionValue = this.inputs.horarioDecoracion.value.trim();
    const nota = this.inputs.notaEvento.value.trim();
    const horasExtras = Number(this.inputs.cantidadHorasExtras.value);
    let evento = new Evento({
      idCliente: Number(this.cliente.idCliente),
      horarioInicio: horarioInicio,
      horarioFinal: horarioFin,
      decorar: this.inputs.decorar.checked,
      costo: Number(this.inputs.costoEvento.value),
      especial: this.inputs.esEspecial.checked,
      idUsuarioIngreso: 1,
      fechaUltimaModificacion: new Date(),
      cantidadHoraExtra: horasExtras > 0 ? horasExtras : null,
      fechaIngreso: new Date(),
      costoHoraExtra:
        Number(this.inputs.costoPorHoraExtra.value) === 0
          ? null
          : Number(this.inputs.costoPorHoraExtra.value),
      horarioDecoracion:
        horarioDecoracionValue === ""
          ? null
          : new Date(
            `${new Date().toISOString().split("T")[0]
            }T${horarioDecoracionValue}:00`
          ),
      notasEvento: nota.length > 0 ? nota : null,
    });

    return evento;
  }

  obtenerEventoParaEditar() {
    const fecha = new Date(this.fechaSelected).toISOString().split("T")[0];
    const horaInicio = this.inputs.horarioInicio.value; // "15:00"
    const horaFin = this.inputs.horarioFin.value; // "22:00"
    const horarioInicio =
      fecha && horaInicio ? `${fecha}T${horaInicio}:00` : null;
    const horarioFinal = fecha && horaFin ? `${fecha}T${horaFin}:00` : null; // const horarioInicio = (this.fechaSelected && horaInicio) ? new Date(`${this.fechaSelected}T${horaInicio}:00`) : null;
    // const horarioFin = (this.fechaSelected && horaFin) ? new Date(`${this.fechaSelected}T${horaFin}:00`) : null;
    const horarioDecoracionValue = this.inputs.horarioDecoracion.value.trim();
    const nota = this.inputs.notaEvento.value.trim();
    const horasExtras = Number(this.inputs.cantidadHorasExtras.value);
    // let evento = new Evento({
    //   // idEvento: this.evento.idEvento || null,
    //   // cliente: {idCliente: this.cliente.idCliente},
    //   idCliente: Number(this.cliente.idCliente),
    //   horarioInicio: horarioInicio,
    //   horarioFinal: horarioFinal,
    //   decoracion: this.inputs.decorar.checked,
    //   costo: Number(this.inputs.costoEvento.value),
    //   especial: this.inputs.esEspecial.checked,
    //   idUsuarioIngreso: 1,
    //   fechaUltimaModificacion: new Date(),
    //   cantidadHoraExtra: horasExtras > 0 ? horasExtras : null,
    //   fechaIngreso: new Date(),
    //   costoHoraExtra:
    //     Number(this.inputs.costoPorHoraExtra.value) === 0
    //       ? null
    //       : Number(this.inputs.costoPorHoraExtra.value),
    //   horarioDecoracion:
    //     horarioDecoracionValue === ""
    //       ? null
    //       : new Date(
    //           `${
    //             new Date().toISOString().split("T")[0]
    //           }T${horarioDecoracionValue}:00`
    //         ),
    //   notasEvento: nota.length > 0 ? nota : null,
    // });
    let evento = {};
    evento.idEvento = this.evento.idEvento;
    evento.cliente = this.cliente;
    // evento.idCliente = Number(this.cliente.idCliente);
    if (horarioInicio) evento.horarioInicio = horarioInicio;
    if (horarioFinal) evento.horarioFinal = horarioFinal;
      evento.decoracion = this.inputs.decorar.checked;
    if (this.inputs.costoEvento.value)
      evento.costo = Number(this.inputs.costoEvento.value);
    evento.especial = this.inputs.esEspecial.checked;
    evento.usuarioIngreso = { idUsuario: 1 , nombre : "Cesar", apellidoPaterno: "Vazquez", apellidoMaterno: "Soto", idRol : 1, telefono: "8673269900"};
    evento.usuarioModificacion = { idUsuario: 1 , nombre : "Cesar", apellidoPaterno: "Vazquez", apellidoMaterno: "Soto", idRol : 1, telefono: "8673269900"};
    evento.cantidadHoraExtra = Number(this.inputs.cantidadHorasExtras.value);
    evento.fechaUltimaModificacion = new Date();
    if (horasExtras > 0) evento.cantidadHoraExtra = horasExtras;

    const costoHoraExtra = Number(this.inputs.costoPorHoraExtra.value);
    if (costoHoraExtra > 0) evento.costoHoraExtra = costoHoraExtra;

    if (horarioDecoracionValue !== "") {
      evento.horarioDecoracion = new Date(
        `${new Date().toISOString().split("T")[0]}T${horarioDecoracionValue}:00`
      );
    }

    if (nota.length > 0) evento.notas = nota;
    console.log("evento para editar: despues de notas:  ", evento);
    return evento;
  }

  onSubmit() {
    // if (!this.validarFormulario()) {
    //   return; // corta el flujo si falla validación
    // }

    let evento;
    if (this.estadoFormulario == "create") {
      evento = this.obtenerEventoParaCrear();
    }
  }

  obtenerClienteParaCrear() {
    let cliente = new Cliente({
      idPropietario: 1,
      telefono: this.inputs.telefonoCliente.value,
      nombre: this.inputs.nombreCliente.value,
      apellidoPaterno: this.inputs.apellidoPaternoCliente.value,
      apellidoMaterno: this.inputs.apellidoMaternoCliente.value,
      idColonia: this.inputs.coloniaCliente.selectedIndex,
      numeroExterior: this.inputs.numeroExterior.value,
      calle: this.inputs.direccionCliente.value,
      fechaAlta: new Date(),
    });

      if (this.cliente != null) {
      cliente.idCliente = this.cliente.idCliente;
    }


    return cliente;
  }

  formatearHora(isoString) {
    if (!isoString) return "";
    const date = new Date(isoString);
    const horas = String(date.getHours()).padStart(2, "0");
    const minutos = String(date.getMinutes()).padStart(2, "0");
    return `${horas}:${minutos}`;
  }

  

}
