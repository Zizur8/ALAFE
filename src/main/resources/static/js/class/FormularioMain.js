import { Evento } from "../class/Evento.js";
import { Cliente } from "../class/Cliente.js";
import { Nota } from "../class/Nota.js";
import { crearNotaComponent } from "../components/notas-component.js";

export class FormularioMain {
  constructor(formId = "form-evento") {
    this.fechaSelected = null;
    this.form = document.getElementById("form-evento");
    this.estadoFormulario = "";
    this.cliente = null;
    this.idCliente = null;
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
      //notaEvento: this.form.querySelector("#notaEvento"),
      costoTotal: this.form.querySelector("#costoTotal"),
      saldoOperacion: this.form.querySelector("#saldoOperacion"),
    };
    this.buttons = {
      mostrarListadoMovimientos: this.form.querySelector(
        "#listado-movimientos-evento"
      ),
      submitEvento: this.form.querySelector("#submit-evento"),
      listMovimientos: this.form.querySelector("#listado-movimientos-evento"),
    };
    this.tags = {
      miniDecorar: this.form.querySelector("#mini-decorar"),
      miniSaldo: this.form.querySelector("#mini-saldo"),
      miniHorasExtras: this.form.querySelector("#mini-horas-extras"),
      miniTieneNotas: this.form.querySelector("#mini-tiene-notas"),
      miniStatusLiquidado: this.form.querySelector("#mini-status-liquidado"),
    };
    this.labels = {
      anticipos: this.form.querySelector("#lblAnticipo"),
      horarioDecoracion: this.form.querySelector("#lblHorarioDecoracion"),
      tasaCambio: this.form.querySelector("#lblTasaCambio"),
      moneda: this.form.querySelector("#lblMoneda"),
      costoPorHoraExtra: this.form.querySelector("#lblCostoPorHoraExtra"),
      cantidadPorHorasExtras: this.form.querySelector(
        "#lblCantidadHorasExtras"
      ),
    };
    this.TipoMovimiento = Object.freeze({
      ANTICIPO: 1,
      ABONO: 2,
      DEVOLUCION: 3,
    });

    this.initListeners();
  }

  initListeners() {
    this.inputs.decorar.addEventListener("change", () =>
      this.actualizarHorarioDecoracion()
    );
    this.inputs.costoEvento.addEventListener("change", () =>
      this.actualizarComponentesMonetarios()
    );
    this.inputs.cantidadHorasExtras.addEventListener("change", () =>
      this.calcularCostoTotal()
    );
    this.inputs.costoPorHoraExtra.addEventListener("change", () =>
      this.calcularCostoTotal()
    );

  }

  limpiarFormulario() {
    console.log("Limpiar formulario");
    Object.values(this.inputs || {}).forEach((input) => {
      if (!input) return;

      const tag = input.tagName?.toLowerCase();

      if (tag === "input" && input.type === "checkbox") {
        input.checked = false;
      } else if (tag === "select") {
        input.selectedIndex = 0;
      } else if ("value" in input) {
        input.value = "";
      }

      input.style.display = "";
    });
    this.inputs.decorar.checked = true;
    this.inputs.costoEvento.value = 0;
    this.actualizarHorarioDecoracion();
    this.actualizarComponentesMonetarios();
    const contenedorNotas = document.getElementById("container-notas");
    this.evento = null;
    this.cliente = null;
    contenedorNotas.innerHTML = "";
    this.renderizarBotonEliminarEvento();
    this.actualizarBotonSubmit();
  }

  llenarFormulario(evento) {
    const cliente = evento ? evento.cliente : null;
    this.idCliente = cliente ? cliente.idCliente : null;
    console.log("evento recibido en llenarFormulario:", evento);
    this.inputs.horarioInicio.value = this.formatearHora(evento?.horarioInicio);
    this.inputs.horarioFin.value = this.formatearHora(evento?.horarioFin);
    this.inputs.horarioDecoracion.value = this.formatearHora(
      evento?.horarioDecoracion
    );
    this.inputs.cantidadHorasExtras.value = Number(
      evento?.cantidadHoraExtras ?? 0
    );
    this.inputs.costoEvento.value = Number(evento?.costo ?? 0);
    console.log("Costo evento llenado:", this.inputs.costoEvento.value);
    this.inputs.anticipo.value = Number(evento?.anticipo ?? 0);
    this.inputs.decorar.checked = !!evento?.decoracion;
    this.inputs.esEspecial.checked = !!evento?.especial;
    this.inputs.telefonoCliente.value = cliente?.telefono ?? "";
    this.inputs.correoCliente.value = cliente?.correo ?? "";
    this.inputs.direccionCliente.value = cliente?.calle ?? "";
    this.inputs.coloniaCliente.value = cliente?.idColonia ?? "";
    this.inputs.numeroExterior.value = cliente?.numeroExterior ?? "";
    this.inputs.nombreCliente.value = cliente?.nombre ?? "";
    this.inputs.apellidoPaternoCliente.value = cliente?.apellidoPaterno ?? "";
    this.inputs.apellidoMaternoCliente.value = cliente?.apellidoMaterno ?? "";
    this.inputs.costoPorHoraExtra.value = evento?.costoHoraExtra ?? "";
  console.log("antes de actualizar componentes anticipos");
    console.log("Despues de actualizar componentes anticipos");
    this.actualizarComponentesMonetarios();
    this.calcularCostoTotal();
    this.actualizarLabelMovimiento();
    this.actualizarComponentesHorasExtras();
    this.llenarNotasEnFormulario(evento?.notas || []);
  }

  llenarNotasEnFormulario(notas) {
    console.log("Llenar notas en formulario:", notas);
    const contenedorNotas = this.form.querySelector("#container-notas");
    contenedorNotas.innerHTML = "";
    notas.forEach((nota) => {
      const newNota = new Nota(
        nota.idEventoNota,
        nota.idEvento,
        nota.nota,
        nota.nombreUsuarioIngreso,
        nota.fechaIngreso
      );
      const notaComponent = crearNotaComponent(newNota);
      contenedorNotas.appendChild(notaComponent);
    });
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

  renderizarBotonEliminarEvento() {
    console.log("Renderizar boton eliminar evento" + this.estadoFormulario);
    const btnEliminar = this.form.querySelector("#delete-evento");
    if (this.estadoFormulario === "edit") {
      btnEliminar.style.display = "inline-block";
      return;
    }
    btnEliminar.style.display = "none";
  }

  actualizarBotonSubmit() {
    console.log("Estado formlario:" + this.estadoFormulario);
    this.buttons.submitEvento.innerHTML =
      this.estadoFormulario === "create" ? "Agendar Evento" : "Guardar Evento";
  }

  actualizarTags() {
    this.tags.miniHorasExtras.innerHTML =
      "H. Extras: " + this.inputs.cantidadHorasExtras.value.trim();
    this.tags.miniHorasExtras.style.display =
      this.inputs.cantidadHorasExtras.value > 0 ? "inline" : "none";
    //this.tags.miniTieneNotas.style.display =
    // this.inputs.notaEvento.value !== "" ? "inline" : "none";
  }

  actualizarHorarioDecoracion() {
    this.inputs.horarioDecoracion.style.display = this.inputs.decorar.checked
      ? "inline"
      : "none";
    this.labels.horarioDecoracion.style.display = this.inputs.decorar.checked
      ? "inline"
      : "none";
  }

  actualizarComponentesHorasExtras() {
    this.labels.costoPorHoraExtra.style.display =
      this.inputs.cantidadHorasExtras.value > 0 ? "inline" : "none";
    this.inputs.costoPorHoraExtra.style.display =
      this.inputs.cantidadHorasExtras.value > 0 ? "inline" : "none";
    this.calcularCostoTotal();
  }

  actualizarComponentesMonetarios() {
    console.log(this.inputs.costoEvento.value + "-----emtodo monerarios");
    const costoEventoValido =
      Number(this.inputs.costoEvento.value) > 0 ? true : false;
    console.log(costoEventoValido + "**********");
    this.calcularCostoTotal();
  }

  calcularCostoTotal() {
    console.log(this.inputs.costoEvento.value + "--------------");
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
    const horarioDecoracionValue = 
      this.inputs.decorar.checked
        ? this.inputs.horarioDecoracion.value.trim()
        : "";

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
      horasExtras: horasExtras > 0 ? horasExtras : null,
      fechaIngreso: new Date(),
      costoHoraExtra:
        Number(this.inputs.costoPorHoraExtra.value) === 0
          ? null
          : Number(this.inputs.costoPorHoraExtra.value),
      horarioDecoracion:
        horarioDecoracionValue === ""
          ? null
          : new Date(
              `${
                new Date().toISOString().split("T")[0]
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
    const horarioDecoracionValue = 
      this.inputs.decorar.checked 
        ? this.inputs.horarioDecoracion.value.trim()
        : "";
        console.log("Horario decoracion value: ", horarioDecoracionValue);
    //const nota = this.inputs.notaEvento.value.trim();
    const horasExtras = Number(this.inputs.cantidadHorasExtras.value);

    let evento = {};
    evento.idAgenda = 1;
    if (this.estadoFormulario == "edit" || this.estadoFormulario == "delete") evento.idEvento = this.evento.idEvento;
    evento.cliente = this.cliente;
    // evento.idCliente = Number(this.cliente.idCliente);
    if (horarioInicio) evento.horarioInicio = horarioInicio;
    if (horarioFinal) evento.horarioFinal = horarioFinal;
    evento.decoracion = this.inputs.decorar.checked;
    if (this.inputs.costoEvento.value)
      evento.costo = Number(this.inputs.costoEvento.value) || 0;
    evento.especial = this.inputs.esEspecial.checked;

    evento.usuarioModificacion = {
      idUsuario: 1,
      nombre: "Cesar",
      apellidoPaterno: "Vazquez",
      apellidoMaterno: "Soto",
      idRol: 1,
      telefono: "8673269900",
    };
    evento.horasExtras = Number(this.inputs.cantidadHorasExtras.value) || 0;
    evento.fechaUltimaModificacion = new Date();
    if (horasExtras > 0) evento.horasExtras = horasExtras;

    evento.costoHoraExtra = Number(this.inputs.costoPorHoraExtra.value) || 0;
    // if (costoHoraExtra > 0) evento.costoHoraExtra = costoHoraExtra;

    if (horarioDecoracionValue !== "") {
      evento.horarioDecoracion = new Date(
        `${new Date().toISOString().split("T")[0]}T${horarioDecoracionValue}:00`
      );
    }

    //if (nota.length > 0) evento.notas = nota;

    //evento.notas = this.obtenerNotasEvento();
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

    console.log("Cliente para crear:", this.cliente);
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

  obtenerNotasEvento() {
    let notas = [];
    if (this.evento != null && this.evento.notas != null) {
      console.log("Notas existentes en el evento:", this.evento.notas);
      notas = this.evento.notas;
    }
    const contenedorNotas = this.form.querySelector("#container-notas");
    for (let notaElemento of contenedorNotas.children) {
      const textarea = notaElemento.querySelector(
        "textarea.notaEventoComponent"
      );
      const notaTexto = textarea.value.trim();
      if (
        this.estadoFormulario === "edit" &&
        notas.length > textarea.dataset.id
      ) {
        console.log("Actualizando nota existente");
        notas[textarea.dataset.id].nota = notaTexto;
        continue;
      }

      const nota = {
        idEventoNota: notas.idEventoNota || null,
        idEvento: this.evento ? this.evento.idEvento : null,
        nota: notaTexto,
        usuarioIngreso: window.AppConfig.usuarioSession,
        fechaIngreso: new Date(),
      };

      notas.push(nota);
      console.log("Nota creadaaaa: ", nota);
    }
    return notas;
  }

  generarMovimientoAnticipo() {
    const movimiento = {
      idCliente: this.cliente.idCliente,
      idEvento: this.evento ? this.evento.idEvento : null,
      monto: Number(this.inputs.anticipo.value),
      idTipoMoneda: this.inputs.moneda.selectedIndex + 1,
      idTipoOperacionMovimiento: this.TipoMovimiento.ANTICIPO,
      tasaCambio:
        this.inputs.moneda.selectedIndex == 0
          ? 1
          : Number(this.inputs.tasaCambio.value),
      idUsuario: window.AppConfig.usuarioSession.idUsuario,
    };
    console.log("Movimiento anticipo generado:", movimiento);
    return movimiento;
  }

  generarMovimientoAbono() {
    const movimiento = {
      idCliente: this.cliente.idCliente,
      idEvento: this.evento ? this.evento.idEvento : null,
      monto: Number(this.inputs.anticipo.value),
      idTipoMoneda: this.inputs.moneda.selectedIndex + 1,
      idTipoOperacionMovimiento: this.TipoMovimiento.ABONO,
      tasaCambio:
        this.inputs.moneda.selectedIndex == 0
          ? 1
          : Number(this.inputs.tasaCambio.value),
      idUsuario: window.AppConfig.usuarioSession.idUsuario,
    };
    console.log("Movimiento abono generado:", movimiento);
    return movimiento;
  }
}
