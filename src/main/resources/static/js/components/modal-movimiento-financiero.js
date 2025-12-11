

export function modalMovimientoFinanciero(options = {}) {
    const {
        labelTitulo = "Nuevo Movimiento Financiero",
        labelMovimiento = "Anticipo",
        onSave = () => {},
        onCancel = () => {}
    } = options;

    const backdrop = document.createElement("div");
    backdrop.classList.add("bg-translucent");

    const modal = document.createElement("div");
    modal.id = "modalMovimiento";
    modal.innerHTML = `
        <div class="header">
            <h2>${labelTitulo}</h2>
        </div>

        <div>
            <div class="anticipo-formaPago-container">
                <div>
                    <label>${labelMovimiento}</label>
                    <input type="number" id="anticipo-modal" placeholder="$" min="0">
                </div>

                <div>
                    <label>Método Pago</label>
                    <select id="forma-pago">
                        <option value="Efectivo">Efectivo</option>
                        <option value="Deposito">Deposito</option>
                        <option value="Transferencia">Transferencia</option>
                    </select>
                </div>
            </div>

            <div class="anticipo-formaPago-container">
                <div>
                    <label>Tasa Cambio</label>
                    <input type="number" id="tasaCambio" step="any" min="0" max="9999" value="18.50">
                </div>

                <div>
                    <label>Moneda</label>
                    <select id="moneda-movimiento">
                        <option value="MXN">MXN</option>
                        <option value="USD">USD</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="footer">
            <button id="cancelar-movimiento">Cancelar</button>
            <button id="guardar-movimiento">Guardar</button>
        </div>
    `;

    modal.querySelector("#cancelar-movimiento").onclick = () => {
        onCancel();
        modal.remove();
        backdrop.remove();
    };

    modal.querySelector("#guardar-movimiento").onclick = () => {
        const data = {
            anticipo: Number(document.getElementById("anticipo-modal").value) || 0,
            formaPago: document.getElementById("forma-pago").value,
            tasaCambio: Number(document.getElementById("tasaCambio").value) || 0,
            moneda: document.getElementById("moneda-movimiento").value
        };

        onSave(data);
        modal.remove();
        backdrop.remove();
    };

    document.body.appendChild(backdrop);
    document.body.appendChild(modal);
}
