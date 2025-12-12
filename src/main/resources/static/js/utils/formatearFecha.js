export function formatearFecha(fecha) {
  const opciones = { day: "numeric", month: "short", year: "numeric" };
  let resultado = fecha.toLocaleDateString("es-ES", opciones);

  // Convertir primera letra del mes a mayúscula
  resultado = resultado.replace(/\b\w/g, c => c.toUpperCase());

  // Reemplazar espacios por "/"
  resultado = resultado.replace(/ /g, "/");

  return resultado;
}
