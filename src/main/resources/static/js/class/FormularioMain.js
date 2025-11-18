export  class FormularioMain {
  constructor(formId = "form-evento") {
    this.form = document.getElementById("form-evento");
    this.inputs = {
      horarioInicio: this.form.querySelector("#horarioInicio"),
      horarioFin: this.form.querySelector("#horarioFin"),
      ocupaDosDias: this.form.querySelector("#ocupaDosDias"),
      decorar: this.form.querySelector("#decorar"),
      horarioDecoracion: this.form.querySelector("#horarioDecoracion"),
      esEspecial: this.form.querySelector("#esEspecial"),
      horasExtras: this.form.querySelector("#horasExtras"),
      costoPorHora: this.form.querySelector("#costoPorHora"),
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
    };
    this.tags = {
      miniDecorar: this.form.querySelector("#mini-decorar"),
      miniSaldo: this.form.querySelector("#mini-saldo"),
      miniHorasExtras: this.form.querySelector("#mini-horas-extras"),
      miniTieneNotas: this.form.querySelector("#mini-tiene-notas"),
      miniStatusLiquidado: this.form.querySelector("#mini-status-liquidado"),
    };
    this.labels = {
        horarioDecoracion: this.form.querySelector("#lblHorarioDecoracion"),
    }
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

    this.actualizarHorarioDecoracion();
    
  }

  llenarFormulario(evento, cliente) {
    this.inputs.horarioInicio.value = this.formatearHora(evento?.horarioInicio);
    this.inputs.horarioFin.value = this.formatearHora(evento?.horarioFin);
    this.inputs.horasExtras.value = Number(evento?.cantidadHoraExtras ?? 0);
    console.log('evento ------> ',evento);
    console.log('costo ------> ',evento.costo);
    this.inputs.costoEvento.value = Number(evento?.costo ?? 0);

    this.inputs.decorar.checked = !!evento?.decoracion;
    this.inputs.horarioDecoracion.style.display = this.inputs.decorar.checked ? "inline" : "none";
    this.labels.horarioDecoracion.style.display = this.inputs.decorar.checked ? "inline" : "none";
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
    
  }

  actualizarTags() {
    this.tags.miniHorasExtras.style.display =
      this.inputs.horasExtras.value > 0 ? "inline" : "none";
    this.tags.miniTieneNotas.style.display =
      this.inputs.notaEvento.value !== "" ? "inline" : "none";

  }

  actualizarHorarioDecoracion(){
    this.inputs.horarioDecoracion.style.display = this.inputs.decorar.checked ? "inline" : "none";
    this.labels.horarioDecoracion.style.display = this.inputs.decorar.checked ? "inline" : "none";
  }


  obtenerDatos() {
    return {
      horarioInicio: this.inputs.horarioInicio.value,
      horarioFin: this.inputs.horarioFin.value,
      ocupaDosDias: this.inputs.ocupaDosDias.checked,
      decorar: this.inputs.decorar.checked,
      horarioDecoracion: this.inputs.horarioDecoracion.value,
      esEspecial: this.inputs.esEspecial.checked,
      horasExtras: Number(this.inputs.horasExtras.value),
      costoPorHora: Number(this.inputs.costoPorHora.value),
      costoEvento: Number(this.inputs.costoEvento.value),
      anticipo: Number(this.inputs.anticipo.value),
      moneda: this.inputs.moneda.value,
      tasaCambio: Number(this.inputs.tasaCambio.value),
      cliente: {
        telefono: this.inputs.telefonoCliente.value,
        correo: this.inputs.correoCliente.value,
        direccion: this.inputs.direccionCliente.value,
        colonia: this.inputs.coloniaCliente.value,
        numeroExterior: this.inputs.numeroExterior.value,
        nombre: this.inputs.nombreCliente.value,
        apellidoPaterno: this.inputs.apellidoPaternoCliente.value,
        apellidoMaterno: this.inputs.apellidoMaternoCliente.value,
      },
      notasEvento: this.inputs.notaEvento.value,
    };
  }

  formatearHora(isoString) {
    if (!isoString) return "";
    const date = new Date(isoString);
    const horas = String(date.getHours()).padStart(2, "0");
    const minutos = String(date.getMinutes()).padStart(2, "0");
    return `${horas}:${minutos}`;
  }
}
