document.addEventListener("change", () => {
  const checkBox = document.getElementById("ocupaDosDias");
  const warningLabel = document.getElementById("warning-ocupada-dos-dias");
  const horarioInicio = document.getElementById("horarioInicio");
  const horarioFin = document.getElementById("horarioFin");
  // const infoDateLabel = document.getElementById("currentDateTitle");

  if (checkBox && warningLabel) {
    if (checkBox.checked) {
      warningLabel.style.display = "block";

      horarioInicio.type = "datetime-local";
      horarioFin.type = "datetime-local";
            horarioInicio.value = "2025-10-27T02:00";
      horarioFin.value = "2025-10-29T07:00";
      // infoDateLabel.style.display = "none"
    } else {
      warningLabel.style.display = "none";
      horarioInicio.type = "time";
      horarioFin.type = "time";
      //infoDateLabel.style.display = "inline"
    }
  }
});

// const input = document.querySelector("[data-enable]");
// const targetId = checkbox.dataset.enable;
// const target = document.getElementById(targetId);

// checkbox.addEventListener("change", () => {
//   target.style.display = checkbox.checked ? "block" : "none";
// });

// document.addEventListener("input", (event) => {
//   const input = event.target;

//   if (input.matches("[data-toggle]")) {
//     const ids = input.dataset.toggle.split(" ");
//     const mostrar = parseFloat(input.value) > 0;

//     ids.forEach((id) => {
//       const campo = document.getElementById(id);
//       const etiqueta = document.querySelector(`label[for="${id}"]`);

//       if (campo) campo.classList.toggle("ocultar-default", !mostrar);
//       if (etiqueta) etiqueta.classList.toggle("ocultar-default", !mostrar);
//     });
//   }
// });
