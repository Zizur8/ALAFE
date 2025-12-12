
class CustomAside extends HTMLElement {
  connectedCallback() {
    this.innerHTML = `
    <aside id="aside-app">
        <div>
            <ul id="modulos-list">
                <li><a href="#">Clientes</a></li>
                <li><a href="#">Movimientos</a></li>
                <li><a href="#">Reportes</a></li>
                <li><a href="#">Usuarios</a></li>

            </ul>
        </div>
        <div id="buttons-container-aside">
            <button class="button-icons-aside">
                <i class="fa-solid fa-user"></i>
            </button>
            <button class="button-icons-aside">
                <i class="fa-solid fa-gear"></i>
        </div>
    </aside>
    `;
  }


}
customElements.define('custom-aside', CustomAside);
