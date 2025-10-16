    let currentDate = new Date();
    let selectedDate = null;

    const months = [
        'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
    ];

    function renderCalendar() {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();

        // Actualizar título
        document.getElementById('monthYear').textContent = `${months[month]} ${year}`;

        // Limpiar grid
        const daysGrid = document.getElementById('daysGrid');
        daysGrid.innerHTML = '';

        // Primer día del mes y último día
        const firstDay = new Date(year, month, 1);
        const lastDay = new Date(year, month + 1, 0);
        const daysInMonth = lastDay.getDate();
        const firstDayOfWeek = firstDay.getDay();

        // Agregar espacios vacíos para los días antes del primer día del mes
        for (let i = 0; i < firstDayOfWeek; i++) {
            const emptyElement = document.createElement('div');
            emptyElement.className = 'day empty';
            daysGrid.appendChild(emptyElement);
        }

        // Generar solo los días del mes actual
        for (let day = 1; day <= daysInMonth; day++) {
            const date = new Date(year, month, day);

            const dayElement = document.createElement('div');
            dayElement.className = 'day';
            dayElement.textContent = day;

            // Marcar día actual
            const today = new Date();
            if (date.toDateString() === today.toDateString()) {
                dayElement.classList.add('today');
            }

            // Marcar día seleccionado
            if (selectedDate && date.toDateString() === selectedDate.toDateString()) {
                dayElement.classList.add('selected');
            }

            // Agregar evento click
            dayElement.addEventListener('click', () => selectDate(date));

            daysGrid.appendChild(dayElement);
        }
    }

    function selectDate(date) {
        selectedDate = date;
        renderCalendar();
    }

    function previousMonth() {
        currentDate.setMonth(currentDate.getMonth() - 1);
        renderCalendar();
    }

    function nextMonth() {
        currentDate.setMonth(currentDate.getMonth() + 1);
        renderCalendar();
    }

    // Inicializar calendario
    renderCalendar();