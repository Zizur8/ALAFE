import { Evento } from "../class/Evento.js";
import { Cliente } from "../class/Cliente.js";
import { Nota } from "../class/Nota.js";
import { FormularioMain } from "../class/FormularioMain.js";
import { crearNotaComponent } from "../components/notas-component.js";
import { modalMovimientoFinanciero } from "../components/modal-movimiento-financiero.js";
import { modalCliente } from "../components/modal-cliente-component.js";

window.AppConfig = {
  usuarioSession: {
    idUsuario: 1,
    nombre: "Cesar",
    apellidoPaterno: "Vazquez",
    apellidoMaterno: "Soto",
    idRol: 1,
    telefono: "867 326 9900",
    propietario: 1,
    correo: null,
  },
  agenda: {
    idAgenda: 1,
    nombreAgenda: "Morena Linda",
    descripcion: "Agenda del restaurant Morena Linda",
    numeroExterior: "3454",
    calle: "Av. Ocampo",
    fechaAlta: "2025-11-25T23:00:01.173",
  },
};

const contenedorNotas = document.getElementById("container-notas");
const calendarComponent = document.querySelector("custom-calendar");
const formulario = new FormularioMain();
formulario.actualizarTags();
formulario.limpiarFormulario();
window.addEventListener("DOMContentLoaded", ajustarAncho);


const calendar = document.querySelector("custom-calendar");
const salida = document.getElementById("selectedDate");


function formatLocalISO(date) {
  const pad = (n) => String(n).padStart(2, "0");
  return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}T${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
}

async function obtenerEventosRangoPorMes(date) {
  let horarioInicio = new Date(date);
  let horarioFin = new Date(date);

  horarioInicio.setHours(0, 0, 0, 0);
  horarioFin.setHours(23, 59, 59, 999);

  const inicioLocal = formatLocalISO(horarioInicio);
  const finLocal = formatLocalISO(horarioFin);

  console.log("Fechas para fetch:", inicioLocal, finLocal);

  const response = await fetch(
    `/alafe/v1/evento?horarioInicio=${inicioLocal}&horarioFin=${finLocal}`
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
      formulario.renderizarBotonEliminarEvento();
      return;
    }

    console.log("Evento cargado:", eventoFetch);
    // const evento = fabricarEvento(eventoFetch);
    const evento = new Evento(eventoFetch[0]);
    console.log("Evento objeto: ---->", evento);

    formulario.estadoFormulario = "edit";
    formulario.evento = evento;
    formulario.cliente = evento.cliente;
    console.log("Eventaaaaaaaaaaaaaaaaa");
    formularioCargado();
  } catch (error) {
    console.error("No se pudo cargar el evento:", error);
    formulario.estadoFormulario = "create";
  }
}

function formularioCargado() {
  formulario.llenarFormulario(formulario.evento);
  formulario.actualizarTags();
  formulario.actualizarBotonSubmit();
  formulario.renderizarBotonEliminarEvento();
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

// Inicializar calendario
document.addEventListener("DOMContentLoaded", () => {});

const calendario = document.querySelector("custom-calendar");

calendario.addEventListener("fecha-seleccionada", (event) => {
  formulario.fechaSelected = event.detail.date;
  formulario.limpiarFormulario();
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
  console.log("Cliente prueba: ", cliente);

  let movimiento;

  if (formulario.estadoFormulario == "edit") {
    console.log("Modo EDIT");
    evento = formulario.obtenerEventoParaEditar();
    evento.notas = formulario.obtenerNotasEvento();
    evento.agenda = window.AppConfig.agenda;
    formulario.evento = evento;
    editarEvento();
    console.log("Evento ediiiit: ", formulario.evento);
    //movimiento = formulario.generarMovimientoAbono();
  } else if (formulario.estadoFormulario == "create") {
    console.log("Modo creaar");
    evento = formulario.obtenerEventoParaEditar();
    evento.notas = formulario.obtenerNotasEvento();
    evento.agenda = window.AppConfig.agenda;
    formulario.evento = evento;
    const nuevoEvento = crearEvento();
    console.log("Evento nuevo-----response: ", nuevoEvento);
    formulario.evento = nuevoEvento;
    console.log("Evento creadooooo: ", formulario.evento);
    //movimiento = formulario.generarMovimientoAnticipo();
  }

  console.log("aaa-----", formulario.evento);
  if (formulario.movimientoRegistrado != null) {
      formulario.movimientoRegistrado.idEvento = formulario.evento.idEvento;
  formulario.movimientoRegistrado.idCliente = formulario.cliente.idCliente;
  formulario.movimientoRegistrado.idUsuarioIngreso = window.AppConfig.usuarioSession.idUsuario;
  crearTransaccion(formulario.movimientoRegistrado);
  formulario.movimientoRegistrado = null;
  }


  //   if (formulario.evento.notasEvento !== "") {
  // editarEventoNota(evento);
  //   } else {
  //     crearEventoNota(evento);
  //   }

  console.log("Evento enviado: ", evento);
  formulario.limpiarFormulario();
});

function crearTransaccion(movimiento) {
  fetch(`/alafe/v1/movimiento`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(movimiento),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al crear el movimiento");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Movimiento creado con éxito:", data);
      alert("Movimiento creado correctamente con ID: " + data.idMovimiento);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
    });
}

function crearEvento() {
  console.log("Evento en formulario crear:", formulario.evento);

  fetch(`/alafe/v1/evento`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(formulario.evento),
  })
    .then( async(response) => {
      if (!response.ok) {
        const errorData = await response.json().catch(() => null);
        const message = errorData?.message || "Error al agendar el evento"
        throw new Error(message);
      }
      return response.json(); // ✅ leer JSON una sola vez
    })
    .then((data) => {
      // ✅ ahora sí tienes el objeto evento completo
      formulario.evento = data;
      formulario.cliente = data.cliente;

      console.log("Evento creado:", formulario.evento);
      alert("Evento creado correctamente con ID: " + data.idEvento);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("Error al agendar el evento: ", error.message);
    });
}

function editarEvento() {
  console.log("Evento en formulario editar:", formulario.evento);

  fetch(`/alafe/v1/evento/${formulario.evento.idEvento}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(formulario.evento),
  })
    .then(async (response) => {
      if (!response.ok) {
        const errorData = await response.json().catch(() => null);
        const message = errorData?.message || "Error al editar el evento";
        throw new Error(message);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Evento editado con éxito:", data);
      alert("Evento Editado correctamente con ID: " + data.id);
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo editar el evento: " + error.message);
    });
}


document
  .getElementById("cantidadHorasExtras")
  .addEventListener("input", () =>
    formulario.actualizarComponentesHorasExtras()
  );

  document
  .getElementById("costoPorHoraExtra")
  .addEventListener("input", () =>
    formulario.actualizarComponentesHorasExtras()
  );



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
      formulario.idCliente = cliente.idCliente;
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

function crearNota() {
  const idEvento = formulario.evento?.idEvento ?? null;
  const nombreUsuarioIngreso = `${window.AppConfig.usuarioSession.nombre} ${window.AppConfig.usuarioSession.apellidoPaterno}`;
  const nota = new Nota(null, idEvento, "", nombreUsuarioIngreso);
  const notaElemento = crearNotaComponent(
    nota,
    window.AppConfig.usuarioSession
  );
  contenedorNotas.appendChild(notaElemento);
  //formulario.evento.notas.push(nota);
  console.log(formulario.evento);
  console.log("Nota agregada al contenedor");
}

function eliminarEvento() {
  if (!formulario.evento || !formulario.evento.idEvento) {
    alert("No hay evento seleccionado para eliminar.");
    return;
  }

  fetch(`/alafe/v1/evento/${formulario.evento.idEvento}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al eliminar el evento");
      }
      alert("Evento eliminado correctamente.");
      formulario.limpiarFormulario();
      formulario.estadoFormulario = "create";
      formulario.actualizarBotonSubmit();
    })
    .catch((error) => {
      console.error("Error en la petición:", error);
      alert("No se pudo eliminar el evento.");
    });
}

document.addEventListener("DOMContentLoaded", () => {
  document.addEventListener("click", (e) => {
    if (e.target.classList.contains("btn-eliminar")) {
      const notaItem = e.target.closest(".nota-evento-item");
      if (notaItem) notaItem.remove();
    }
    if (e.target.id === "btnAgregarNotaEvento") {
      crearNota();
    }
    if (e.target.id === "btnMostrarOcultarNotasEvento") {
      contenedorNotas.className =
        contenedorNotas.className === "notas-ocultas"
          ? "notas-mostradas"
          : "notas-ocultas";
    }
  });
});

document.getElementById("btn-agregar-pago").addEventListener("click", () => {
  const tituloMovimiento =
    formulario.estadoFormulario === "create" ? "Anticipo" : "Abono";
  const titulo = "Nuevo " + tituloMovimiento;
  abrirModalMovimientoFinanciero(titulo);
});
document.getElementById("btn-cliente-evento").addEventListener("click", () => {

  const titulo = "Nuevo " + "Cliente";
  abrirModalCliente(titulo);
});





function abrirModalMovimientoFinanciero(titulo) {
  modalMovimientoFinanciero({
    labelTitulo: titulo,
    onSave: (data) => {
      console.log("Datos guardados:", data);
      formulario.movimientoRegistrado = data;
      formulario.inputs.nuevoPago.value = Number(data.monto ?? 0);
      formulario.calcularSaldo();
    },
    onCancel: () => {
      console.log("Cancelado");
    },
  });
}

function abrirModalCliente(titulo){
  modalCliente({
    labelTitulo:titulo
  });
}

document.getElementById("delete-evento").addEventListener("click", (e) => {
  console.log("Botón clicado:", e.currentTarget.id);
  formulario.estadoFormulario = "delete";
  const evento = formulario.obtenerEventoParaEditar();
  formulario.evento = evento;
  console.log(formulario.evento);
  eliminarEvento();
  console.log("Evento eliminado: ", formulario.evento);
});