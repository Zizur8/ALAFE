class CustomCalendar extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `
      <div id="agenda-container">
        <div id="agenda-calendario" class="calendar-container">
          <div class="calendar-header">
            <button id="btnPrev" class="nav-button">‹</button>
            <div class="month-year" id="monthYear"></div>
            <button id="btnNext" class="nav-button">›</button>
          </div>
          <div class="calendar-grid">
            <div class="weekdays">
              <div class="weekday">Dom</div>
              <div class="weekday">Lun</div>
              <div class="weekday">Mar</div>
              <div class="weekday">Mié</div>
              <div class="weekday">Jue</div>
              <div class="weekday">Vie</div>
              <div class="weekday">Sáb</div>
            </div>
            <div class="days-grid" id="daysGrid"></div>
          </div>
        </div>
      </div>
    `;

    this.currentDate = new Date();
    this.selectedDate = new Date();
    this.querySelector("#btnPrev").addEventListener("click", () =>
      this.previousMonth()
    );
    this.querySelector("#btnNext").addEventListener("click", () =>
      this.nextMonth()
    );

    this.renderCalendar();
  }

  previousMonth() {
    this.currentDate.setMonth(this.currentDate.getMonth() - 1);
    this.renderCalendar();
  }

  nextMonth() {
    this.currentDate.setMonth(this.currentDate.getMonth() + 1);
    this.renderCalendar();
  }

  async colorearDiasConEvento() {
    const year = this.currentDate.getFullYear();
    const month = this.currentDate.getMonth();

    const inicio = new Date(year, month, 1);
    const fin = new Date(year, month + 1, 0);
    inicio.setHours(0, 0, 0, 0);
    fin.setHours(23, 59, 59, 999);

    const res = await fetch(
      `/alafe/v1/evento?horarioInicio=${inicio.toISOString()}&horarioFin=${fin.toISOString()}`
    );
    if (!res.ok) return;
    const eventos = await res.json();

    const diasConEventos = new Set(
      eventos
        .filter((e) => e && e.horarioInicio)
        .map((e) => new Date(e.horarioInicio))
        .filter((d) => d.getFullYear() === year && d.getMonth() === month)
        .map((d) => d.getDate())
    );

    this.querySelectorAll("#daysGrid .day").forEach((diaEl) => {
      if (diaEl.classList.contains("empty")) return;
      const numeroDia = parseInt(diaEl.textContent.trim(), 10);

      if (Number.isNaN(numeroDia)) return;

      if (diasConEventos.has(numeroDia)) {
        diaEl.classList.add("evento-dia");
      } else {
        diaEl.classList.remove("evento-dia");
      }
    });
  }

  renderCalendar() {
    const year = this.currentDate.getFullYear();
    const month = this.currentDate.getMonth();

    const monthYear = this.querySelector("#monthYear");
    monthYear.textContent = this.currentDate.toLocaleString("default", {
      month: "long",
      year: "numeric",
    });

    const daysGrid = this.querySelector("#daysGrid");
    daysGrid.innerHTML = "";

    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const daysInMonth = lastDay.getDate();
    const firstDayOfWeek = firstDay.getDay();

    for (let i = 0; i < firstDayOfWeek; i++) {
      const emptyElement = document.createElement("div");
      emptyElement.className = "day empty";
      daysGrid.appendChild(emptyElement);
    }

    for (let day = 1; day <= daysInMonth; day++) {
      const date = new Date(year, month, day);
      const dayElement = document.createElement("div");
      dayElement.className = "day";
      dayElement.textContent = day;

      const today = new Date();
      if (date.toDateString() === today.toDateString()) {
        dayElement.classList.add("today");
      }

      if (
        this.selectedDate &&
        date.toDateString() === this.selectedDate.toDateString()
      ) {
        dayElement.classList.add("selected");
      }

      dayElement.addEventListener("click", () => {
        this.selectedDate = date;
        this.renderCalendar();
      });

      daysGrid.appendChild(dayElement);
    }

    this.dispatchEvent(
      new CustomEvent("fecha-seleccionada", {
        detail: { date: this.selectedDate },
        bubbles: true,
        composed: true,
      })
    );
    this.colorearDiasConEvento().catch((err) =>
      console.error("Colorear error:", err)
    );
  }
}

customElements.define("custom-calendar", CustomCalendar);
