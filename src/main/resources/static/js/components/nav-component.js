class CustomNav extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `
        <nav class="">
            <div id="nav-container">
                <div id="dropdown-menu" class="">
                    <button class="" type="button">
                        <i class="fa-solid fa-bars"></i>
                    </button>
                </div>
                <div id="last-update">
                    <h3>15 Febrero</h3>
                    <p id="info-ultimo-registro">Ultimo registro: Victor H.</p>
                </div>

            </div>
        </nav>
    `;
    this.querySelector("#dropdown-menu").addEventListener("click", () => this.toggleDropdown());
  }

  toggleDropdown() {
    const dropdown = document.getElementById("aside-app");

    if (!dropdown) return;

    dropdown.classList.toggle("show");
    dropdown.style.visibility = dropdown.classList.contains("show") ? "visible" : "hidden";
  }
}
customElements.define("custom-nav", CustomNav);


// const months = [
//   "Enero",
//   "Febrero",
//   "Marzo",
//   "Abril",
//   "Mayo",
//   "Junio",
//   "Julio",
//   "Agosto",
//   "Septiembre",
//   "Octubre",
//   "Noviembre",
//   "Diciembre",
// ];

// function renderCalendarMonthYear() {
//   const year = currentDate.getFullYear();
//   let month = 0;

//   const daysGrid = document.getElementsByClassName("calendar-container");
//   for (let i = 0; i < 12; i++) {
//     renderCalendar(year, month);
//     month++;
//   }
// }



// function renderCalendar() {
//   const template = document.getElementById("template-calendario-agenda");

//   // Instanciar el contenido del template
//   const clone = template.content.cloneNode(true);

//   // Referencias dentro del template
//   const calendarioMes = clone.querySelector(".calendar-container");
//   const monthYear = calendarioMes.querySelector("#monthYear");
//   const daysGrid = calendarioMes.querySelector("#daysGrid");

//   // Actualizar el título
//   const months = [
//     "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
//     "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
//   ];
//   monthYear.textContent = `${months[month]} ${year}`;

//   // Limpiar días previos
//   daysGrid.innerHTML = "";

//   // Calcular días del mes
//   const firstDay = new Date(year, month, 1);
//   const lastDay = new Date(year, month + 1, 0);
//   const daysInMonth = lastDay.getDate();
//   const firstDayOfWeek = firstDay.getDay();

//   // Espacios vacíos antes del primer día
//   for (let i = 0; i < firstDayOfWeek; i++) {
//     const empty = document.createElement("div");
//     empty.className = "day empty";
//     daysGrid.appendChild(empty);
//   }

//   // Crear días del mes
//   const today = new Date();
//   for (let day = 1; day <= daysInMonth; day++) {
//     const date = new Date(year, month, day);
//     const dayElement = document.createElement("div");
//     dayElement.className = "day";
//     dayElement.textContent = day;

//     // Día actual
//     if (date.toDateString() === today.toDateString()) {
//       dayElement.classList.add("today");
//     }

//     // Día seleccionado (opcional, si usas selectedDate)
//     if (window.selectedDate && date.toDateString() === window.selectedDate.toDateString()) {
//       dayElement.classList.add("selected");
//     }

//     // Click para seleccionar fecha
//     dayElement.addEventListener("click", () => selectDate(date));

//     daysGrid.appendChild(dayElement);
//   }

//   // Agregar el calendario renderizado al contenedor principal
//   const container = document.getElementById("contenedor-calendario");
//   container.innerHTML = ""; // limpiar si ya había uno
//   container.appendChild(clone);
// }