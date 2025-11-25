import { Evento } from "../class/Evento.js";
import { Cliente } from "../class/Cliente.js";
import { FormularioMain } from "../class/FormularioMain.js";
const calendarComponent = document.querySelector("custom-calendar");
const formulario = new FormularioMain();
let currentDate = new Date();
let selectedDate = null;
formulario.limpiarFormulario();
formulario.actualizarTags();
window.addEventListener("DOMContentLoaded", ajustarAncho);

// await cargarEvento(currentDate);

const calendar = document.querySelector("custom-calendar");
const salida = document.getElementById("selectedDate");



async function selectDate(date) {
  selectedDate = date;
  calendarComponent.renderCalendar();

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


async function obtenerEventosRangoPorMes(date) {
    const horarioInicio = new Date(date);
    const horarioFin = new Date(date);
    horarioInicio.setHours(0, 0, 0, 0);
    horarioFin.setHours(23, 59, 59, 999);
    console.log("Fechas para fetch:", horarioInicio, horarioFin);
      const response = await fetch(
      `/alafe/v1/evento?horarioInicio=${horarioInicio.toISOString()}&horarioFin=${horarioFin.toISOString()}`
    );
    return response;
} 

async function obtenerCliente(id) {
  const clienteResponse = await fetch(`/alafe/v1/cliente/${id}`);
  return clienteResponse;
}   

async function cargarEvento(date) {
  try {
    console.log("Cargando evento para la fecha:", date);
    const response = await obtenerEventosRangoPorMes(date);
    if (!response.ok) throw new Error("Error al consultar evento");
    const eventoFetch = await response.json();

    if (eventoFetch == null || eventoFetch.length === 0) {
      console.log("no hay evento");
      formulario.estadoFormulario = "create";
      formulario.actualizarBotonSubmit();
      return;
    }

    console.log("Evento cargado:", eventoFetch);
    // const evento = fabricarEvento(eventoFetch);
    const evento = new Evento(eventoFetch[0]);
    console.log("Evento objeto: ---->", evento);

    let clienteFetch;
    const clienteId = evento?.idCliente || evento.idCliente;
    if (clienteId) {
      const clienteResponse = await obtenerCliente(clienteId);
      if (!clienteResponse.ok) throw new Error("Error al consultar cliente");
      clienteFetch = await clienteResponse.json();
    }

    if (clienteFetch == null && clienteFetch == undefined) {
      return;
    }
    console.log("Cliente fetch:", clienteFetch);

    const cliente = fabricarCliente(clienteFetch);
    console.log("Cliente objeto:", cliente);

    formulario.estadoFormulario = "edit";
    formulario.cliente = cliente;
    formulario.evento = evento;
    formulario.llenarFormulario(evento, cliente);
    formulario.actualizarTags();
    formulario.actualizarBotonSubmit();
  } catch (error) {
    console.error("No se pudo cargar el evento:", error);
    formulario.limpiarFormulario();
    formulario.estadoFormulario = "";
  }
}

function buscarNotas(evento) {
  fetch(`/alafe/v1/eventoNota?idEvento=${evento.idEvento}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(eventoNota),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al editar el evento");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Evento editado con éxito:", data);
      alert("Evento creado correctamente con ID: " + data.id);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo editar el evento");
    });
}

function fabricarCliente(clienteData) {
  const cliente = new Cliente();
  cliente.idCliente = clienteData.idCliente || null;
  cliente.nombre = clienteData.nombre || null;
  cliente.apellidoPaterno = clienteData.apellidoPaterno || null;
  cliente.apellidoMaterno = clienteData.apellidoMaterno || null;
  cliente.telefono = clienteData.telefono || null;
  cliente.direccion = clienteData.calle || null;
  cliente.colonia = clienteData.idColonia || null;
  cliente.numeroExterior = clienteData.numeroExterior || null;
  cliente.correo = clienteData.correo || null;
  return cliente;
}

function fabricarEvento(eventoData) {
  const evento = new Evento();
  evento.idEvento = eventoData[0].idEvento || null;
  evento.idCliente = eventoData[0].idCliente || null;
  evento.usuario = eventoData[0].usuario || null;
  evento.horarioInicio = eventoData[0].horarioInicio || null;
  evento.horarioFin = eventoData[0].horarioFin || null;
  evento.costo = eventoData[0].costo || 0.0;
  evento.costoHoraExtra = eventoData[0].costoHoraExtras || null;
  evento.tasaCambio = eventoData[0].tasaCambio || 1;
  evento.moneda = eventoData[0].moneda || "";
  evento.cantidadHoraExtras = eventoData[0].cantidadHoraExtra || 0;
  evento.especial = Boolean(eventoData[0].esEspecial);
  evento.decorar = eventoData[0].decorar || false;
  evento.ocupaDosDias = eventoData[0].ocupaDosDias || false;
  evento.notasEvento = eventoData[0].notas || null;
  return evento;
}

// Inicializar calendario
document.addEventListener("DOMContentLoaded", () => {

});

const calendario = document.querySelector("custom-calendar");

calendario.addEventListener("fecha-seleccionada", (event) => {
  formulario.limpiarFormulario();
  formulario.fechaSelected = event.detail.date;

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
  let evento;
  let eventoNota = {};
  let cliente = formulario.cliente;

  cliente = formulario.obtenerClienteParaCrear();
  formulario.cliente = cliente;
  console.log("Cliente prueba: ",cliente);
  
  if (formulario.estadoFormulario == "edit") {
    console.log("Modo EDIT");
    evento = formulario.obtenerEventoParaEditar();
    formulario.evento = evento;
    editarEvento();
  } else {
    console.log("Modo creaar");
    evento = formulario.obtenerEventoParaEditar();
    formulario.evento = evento;
    crearEvento();
  }

  //   if (formulario.evento.notasEvento !== "") {
  // editarEventoNota(evento);
  //   } else {
  //     crearEventoNota(evento);
  //   }

  console.log("Evento enviado: ", evento);
});

function crearEvento() {
  console.log("Evento en formulario crear:" + formulario.evento);
  fetch(`/alafe/v1/evento`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(formulario.evento),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al crear el evento");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Evento creado con éxito:", data);
      alert("Evento creado correctamente con ID: " + data.idEvento);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo editar el evento");
    });
}
function editarEvento() {
  console.log("Evento en formulario editar:" + formulario.evento);
  fetch(`/alafe/v1/evento/${formulario.evento.idEvento}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(formulario.evento),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al editar el evento");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Evento editado con éxito:", data);
      alert("Evento Editado correctamente con ID: " + data.id);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo editar el evento");
    });
}

function crearEventoNota(evento) {
  let e = {};
  e.idEvento = evento.idEvento;
  let eventoNota = {
    e,
    nota: evento.notasEvento,
  };
  console.log("/////", evento);
  fetch(`/alafe/v1/eventoNota`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(evento),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al editar el evento");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Nota creada con éxito:", data);
      alert("Evento creado correctamente con ID: " + data.id);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo editar la Nota");
    });
}

function editarEventoNota(evento) {
  console.log("/////", evento);
  fetch(`/alafe/v1/eventoNota?idEvento=${formulario.evento.idEventoNota}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(evento),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al editar  Nota");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Nota editada con éxito:", data);
      alert("Nota creada correctamente con ID: " + data.id);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo editar la nota");
    });
}

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

//document.getElementById("horasExtras").addEventListener("change", () => formulario.actualizarComponentesMonetarios());
document
  .getElementById("cantidadHorasExtras")
  .addEventListener("change", () =>
    formulario.actualizarComponentesHorasExtras()
  );

async function buscarClientePorTelefono() {
  const telefono = document.getElementById("telefonoCliente").value.trim();

  if (telefono.length < 3) {
    console.log("Escribe al menos 3 dígitos");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:8080/alafe/v1/cliente/telefono/${encodeURIComponent(
        telefono
      )}`
    );
    if (!response.ok) throw new Error("Error en la búsqueda");

    const clientes = await response.json();
    console.log("Resultados:", clientes);

    // aquí puedes mostrar sugerencias o rellenar el formulario
    if (clientes.length > 0) {
      formulario.rellenarCliente(clientes[0]); // ejemplo: tomar el primero
    }
  } catch (error) {
    console.error("Error:", error);
  }
}

const telefonoInput = document.getElementById("telefonoCliente");
const sugerenciasLista = document.getElementById("sugerenciasClientes");

telefonoInput.addEventListener("input", async () => {
      formulario.cliente = null;
    
  const query = telefonoInput.value;
  if (query.length < 3) {
    sugerenciasLista.innerHTML = "";
    return;
  }

  const response = await fetch(`/alafe/v1/cliente/telefono/${query}`);
  const clientes = await response.json();
  console.log("Respuesta del backend:", clientes);
  
  sugerenciasLista.innerHTML = "";

  clientes.forEach((cliente) => {
    const li = document.createElement("li");
    console.log(cliente.nombre);
    li.textContent = `${cliente.telefono} | ${cliente.nombre} ${cliente.apellidoPaterno} ${cliente.apellidoMaterno} | ${cliente.calle}`;
    li.addEventListener("click", () => {
      // al seleccionar, rellena el formulario

      formulario.llenarFormularioConDatosCliente(fabricarCliente(cliente));
      formulario.cliente = cliente;
      sugerenciasLista.innerHTML = "";
    });
    sugerenciasLista.appendChild(li);
  });
});

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
