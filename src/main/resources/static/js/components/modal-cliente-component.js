
export function modalCliente(options = {}) {
    const {
        labelTitulo = ''
    } = options;


        const backdrop = document.createElement("div");
    backdrop.classList.add("bg-translucent");

    const modal = document.createElement("div");
    modal.classList.add("modal-cliente-evento");
    modal.ud = "modalCliente";
    modal.innerHTML = `
        <div>
            <h2>${labelTitulo}</h2>
        </div>
        <div class="section-telefono-correo">

            <div >
                <label for="telefonoCliente">Teléfono</label>
                <input type="text" id="telefonoCliente" name="telefonoCliente" pattern="[0-9]{10}"
                    autocomplete="8673265329" title="Debe contener 10 dígitos numéricos.">
            </div>
            <div>

                <ul id="sugerenciasClientes" class="sugerencias-lista"></ul>
                <label for="correoCliente">Correo</label>
                <input type="email" id="correoCliente" name="correo">
            </div>
        </div>

        <div>
            <label for="nombreCliente">Nombre</label>
            <input type="text" id="nombreCliente" name="nombreCliente">

        </div>
        <div>
            <label for="apellidoPaternoCliente">Apellido Paterno</label>
            <input type="text" id="apellidoPaternoCliente" name="apellidoPaternoCliente">

        </div>
        <div>
            <label for="apellidoMaternoCliente">Apellido Materno</label>
            <input type="text" id="apellidoMaternoCliente" name="apellidoMaternoCliente">
        </div>
        <div>
            <label for="direccionCliente">Dirección</label>
            <input type="text" id="direccionCliente" name="direccionCliente">
        </div>
        <div id="container-direccion-cliente" class="">
            <div>
                <label for="coloniaCliente">Colonia</label>
                <select name="colonia" id="coloniaCliente">
                    <option value="">--</option>
                    <th:block th:each="colonia : ${colonias}">
                        <option th:value="${colonia.idColonia}" th:text="${colonia.nombre}"></option>
                    </th:block>
                </select>
            </div>
            <div>
                <label for="numeroExterior">Num. Exterior</label>
                <input type="text" id="numeroExterior" name="numeroExterior" placeholder="#">
            </div>
        </div>
        <div class="btns-modal-cliente-evento">
            <button id="btn-desasociar-cliente" class="">Desasociar</button>
            <button id="btn-guardar-cliente">Guardar</button>
        </div>
    `;



    document.body.appendChild(backdrop);
    document.body.appendChild(modal);
    cargarColonias();


}




