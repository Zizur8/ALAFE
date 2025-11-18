import { Evento } from "../class/Evento.js";
import { Cliente } from "../class/Cliente.js";
import {FormularioMain} from "../class/FormularioMain.js";
const formulario = new FormularioMain();
formulario.limpiarFormulario();
formulario.actualizarTags();
let currentDate = new Date();
let selectedDate = null;
window.addEventListener("DOMContentLoaded", ajustarAncho);
await cargarEvento(currentDate);

const calendar = document.querySelector("custom-calendar");
const salida = document.getElementById("selectedDate");

calendar.addEventListener("fecha-seleccionada", (e) => {
  const fecha = e.detail.date;
  salida.textContent = fecha.toLocaleDateString("es-MX", {
    day: "numeric",
    month: "long",
  });
});

if (calendar.selectedDate) {
  salida.textContent = calendar.selectedDate.toLocaleDateString("es-MX", {
    day: "numeric",
    month: "long",
  });
}

async function selectDate(date) {

  selectedDate = date;
  renderCalendar();

  await cargarEvento(date);

  //const formNuevoEvento = document.getElementById("form-new-evento");
  //const bgTranslucent = document.getElementById("bg-translucent");

  // if (formNuevoEvento && bgTranslucent) {
  //   formNuevoEvento.style.visibility = "visible";
  //   bgTranslucent.style.visibility = "visible";

  //   // Esperar al siguiente ciclo para evitar que el clic actual lo cierre
  //   setTimeout(() => {
  //     document.addEventListener("click", handleOutsideClick);
  //   }, 0);
  // }

  function handleOutsideClick(e) {
    const clickedInsideForm = formNuevoEvento.contains(e.target);
    const clickedInsideOverlay = bgTranslucent.contains(e.target);

    if (!clickedInsideForm && !clickedInsideOverlay) {
      formNuevoEvento.style.visibility = "hidden";
      bgTranslucent.style.visibility = "hidden";
      document.removeEventListener("click", handleOutsideClick);
    }
  }
}

async function cargarEvento(date) {
  try {
    console.log("Cargando evento para la fecha:", date);
    const horarioInicio = new Date(date);
    const horarioFin = new Date(date);
    horarioInicio.setHours(0, 0, 0, 0);
    horarioFin.setHours(23, 59, 59, 999);

    const response = await fetch(
      `/alafe/v1/evento?horarioInicio=${horarioInicio.toISOString()}&horarioFin=${horarioFin.toISOString()}`
    );
    if (!response.ok) throw new Error("Error al consultar evento");
    const eventoFetch = await response.json();

    if (eventoFetch == null || eventoFetch.length === 0) {
      return;
    }
    console.log("Evento cargado:", eventoFetch);
    const evento = fabricarEvento(eventoFetch);
    let clienteFetch;
    console.log("Evento objeto", evento);
    const clienteId = evento?.idCliente || evento.idCliente;
    if (clienteId) {
      const clienteResponse = await fetch(`/alafe/v1/cliente/${clienteId}`);
      if (!clienteResponse.ok) throw new Error("Error al consultar cliente");

      clienteFetch = await clienteResponse.json();
    }

    if (clienteFetch == null && clienteFetch == undefined) {
      return;
    }
    console.log("Cliente fetch:", clienteFetch);

    const cliente = fabricarCliente(clienteFetch);
    console.log("Cliente objeto:", cliente);
    

    formulario.llenarFormulario(evento, cliente);
    formulario.actualizarTags();
  } catch (error) {
    console.error("No se pudo cargar el evento:", error);
    formulario.limpiarFormulario();
  }
}

function fabricarCliente(clienteData) {
  const cliente = new Cliente();
  cliente.nombre = clienteData.nombre || "";
  cliente.apellidoPaterno = clienteData.apellidoPaterno || "";
  cliente.apellidoMaterno = clienteData.apellidoMaterno || "";
  cliente.telefono = clienteData.telefono || "";
  cliente.direccion = clienteData.calle || "";
  cliente.colonia = clienteData.idColonia || "";
  cliente.numeroExterior = clienteData.numeroExterior || "";
  cliente.correo = clienteData.correo || "";
  return cliente;
}

function fabricarEvento(eventoData) {
  const evento = new Evento();
  evento.idCliente = eventoData[0].idCliente || "";
  evento.horarioInicio = eventoData[0].horarioInicio || "";
  evento.horarioFin = eventoData[0].horarioFin || "";
  evento.costo = eventoData[0].costo || "";
  evento.costoHoraExtra = eventoData[0].costoHoraExtra || "";
  evento.tasaCambio = eventoData[0].tasaCambio || "";
  evento.moneda = eventoData[0].moneda || "";
  evento.cantidadHoraExtras = eventoData[0].cantidadHoraExtras || 0;
  evento.especial = Boolean(eventoData[0].esEspecial);
  evento.decoracion = eventoData[0].decorar || false;
  evento.ocupaDosDias = eventoData[0].ocupaDosDias || "";
  evento.cuentaConNotas = eventoData[0].notaEvento || "";
  evento.notasEvento = eventoData[0].notas || "";
  return evento;
}

// Inicializar calendario
document.addEventListener("DOMContentLoaded", () => {
  const calendario = document.querySelector("custom-calendar");
  if (calendario) {
    calendario.renderCalendar();
  }
});

const calendario = document.querySelector("custom-calendar");

calendario.addEventListener("fecha-seleccionada", (event) => {
  console.log("asdasdasaaaaaaa");
    formulario.limpiarFormulario();
  formulario.actualizarTags();
  cargarEvento(event.detail.date);
});

function ajustarAncho() {
  const inputs = document.querySelectorAll(".autoInput");
  const medidores = document.querySelectorAll(".medidor");

  inputs.forEach((input, i) => {
    const medidor = medidores[i];
    medidor.textContent = input.value || "      ";
    input.style.width = medidor.offsetWidth + "px";

    const extra = 12;
    input.style.width = medidor.offsetWidth + extra + "px";
  });
}

document.addEventListener("submit", function (e) {
  e.preventDefault();

  const formulario = document.getElementById("form-Nuevo-Evento");

  //Campos
  // let ocupaDosDias = document.getElementById("ocupaDosDias").value;
  // let horarioInicio = document.getElementById("horarioInicio").value;
  let horarioFin = document.getElementById("horarioFin").value;
  let decorar = document.getElementById("decorar").value;
  let esEspecial = document.getElementById("esEspecial").value;
  let cantidadHorasExtras = document.getElementById("horasExtras").value;
  let costoPorHora = document.getElementById("costoPorHora").value;
  let costoEvento = document.getElementById("costoEvento").value;
  let moneda = document.getElementById("moneda").value;
  let tasaCambio = document.getElementById("tasaCambio").value;
  let telefono = document.getElementById("telefono").value;
  let correo = document.getElementById("correo").value;
  let direccion = document.getElementById("direccion").value;
  let colonia = document.getElementById("colonia").value;
  let numeroExterior = document.getElementById("numeroExterior").value;
  let nombreCliente = document.getElementById("nombreCliente").value;
  let apellidoPaternoCliente = document.getElementById(
    "apellidoPaternoCliente"
  ).value;
  let apellidoMaternoCliente = document
    .getElementById("apellidoMaternoCliente")
    .value.trim();
  let notaEvento = document.getElementById("notaEvento").value.trim();

  if (horarioInicio > horarioFin) {
    alert("Horario Invalido");
    return;
  }
  if (cantidadHorasExtras < 0) {
    alert("Debe ser mayor o igual a 0");
    return;
  }
  if (costoPorHora < 0) {
    alert("Costo p/h debe ser mayor o igual a 0");
    return;
  }
  if (tasaCambio <= 0) {
    alert("Tasa cambio debe ser mayor o igual a 0");
    return;
  }

  //Validar Usuario
  //Validar usuario
  //Validar Evento
  //Validar EventoPK

  // let EventoPK = new EventoPK(
  //   document.getElementById("input-id-usuario").value,
  //   document.getElementById("input-fecha-evento").value,
  //   document.getElementById("input-hora-evento").value
  // );
});

function llenarFormulario(evento, cliente) {
  console.log("Llenando formulario con evento y cliente:", evento, cliente);
  document.querySelector("#horarioInicio").value = evento.horarioInicio || "";
  document.querySelector("#horarioFin").value = evento.horarioFin || "";
  //document.querySelector("#notaEvento").value = evento.notaEvento || "";
  document.querySelector("#telefonoCliente").value = cliente.telefono || "";
  document.querySelector("#correoCliente").value = cliente.correo || "";
  document.querySelector("#direccionCliente").value = cliente.direccion || "";
  document.querySelector("#coloniaCliente").value = cliente.colonia || "";
  document.querySelector("#numeroExterior").value =
    cliente.numeroExterior || "";
  document.querySelector("#nombreCliente").value = cliente.nombre || "";
  document.querySelector("#apellidoPaternoCliente").value =
    cliente.apellidoPaterno || "";
  document.querySelector("#apellidoMaternoCliente").value =
    cliente.apellidoMaterno || "";

    
document.querySelector("#costoEvento").value =
  Number(evento?.costoEvento) || 0;
  document.querySelector("#costoPorHora").value = evento.costoHoraExtra || "";
  document.querySelector("#tasaCambio").value = evento.tasaCambio || "";
  document.querySelector("#moneda").value = evento.moneda || "";
  document.querySelector("#horasExtras").value =
    evento.cantidadHoraExtras || "";
  document.querySelector("#esEspecial").checked = evento.especial === true;
  document.querySelector("#decorar").value = evento.decoracion || "";
  document.querySelector("#ocupaDosDias").value = evento.ocupaDosDias || "";

  document.querySelector("#notaEvento").value = evento.notasEvento || "";
  console.log("evento.notasEvento:", evento);
  document.getElementById("mini-tiene-notas").style.visibility =
    evento.notasEvento && evento.notasEvento.trim() !== ""
      ? "visible"
      : "hidden";

  const campoHorasExtras = document.getElementById("mini-horas-extras");
  if (evento.cantidadHoraExtras !== 0) {
    campoHorasExtras.style.display = "inline";
    campoHorasExtras.textContent = "H. Extras: " + evento.cantidadHoraExtras;
  } else {
    campoHorasExtras.style.display = "none";
  }
}

function limpiarFormulario() {}

document.getElementById("decorar")
  .addEventListener("change", () => formulario.actualizarHorarioDecoracion());

