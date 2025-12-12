import { formatearFecha } from "../utils/formatearFecha.js";

export function cantidadNotas() {
  const contenedorNotas = document.getElementById("container-notas");
  return contenedorNotas.querySelectorAll(".nota-evento-item").length;
}

export function crearNotaComponent(nota) {
  const notaEvento = document.createElement("div");
  notaEvento.classList.add("nota-evento-item");

  const textareaNota = document.createElement("textarea");
  textareaNota.name = "notaEvento";
  textareaNota.className = "notaEventoComponent";
  textareaNota.value = nota.nota;
  textareaNota.dataset.id = cantidadNotas() == 0 ? 0 : cantidadNotas();

  const infoEventoNota = document.createElement("div");
  infoEventoNota.className = "info-evento-nota";

  const userP = document.createElement("p");
  userP.className = "user-name";
  userP.textContent = nota.nombreUsuarioIngreso;

  const fechaP = document.createElement("p");
  fechaP.className = "nota-fecha-alta";
  const fecha = nota.fechaIngreso ? new Date(nota.fechaIngreso) : new Date();
  fechaP.textContent = formatearFecha(fecha);


  infoEventoNota.appendChild(userP);
  infoEventoNota.appendChild(fechaP);

  const btn = document.createElement("button");
  btn.className = "btn-eliminar";
  btn.type = "button";
  btn.setAttribute("aria-label", "Eliminar");
  btn.textContent = "✖";

  btn.addEventListener("click", () => {
    notaEvento.remove();
  });

  notaEvento.appendChild(textareaNota);
  notaEvento.appendChild(infoEventoNota);
  notaEvento.appendChild(btn);

  return notaEvento;
}
/*
<div class="nota-evento-item">
  <textarea name="notaEvento" class="notaEventoComponent"></textarea>
  <div class="info-evento-nota">
    <p class="user-name">Cesar Vazquez</p>
    <p class="nota-fecha-alta">25/Noviembre/2025</p>
  </div>
  <button class="btn-eliminar" type="button" aria-label="Eliminar">
    ✖
  </button>
</div>;
*/
