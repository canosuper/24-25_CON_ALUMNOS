package modelo

data class PedidoPizzeria(
    val nombre: String,
    val queso: Boolean,
    val bacon: Boolean,
    val cebolla: Boolean,
    val bordeFino: Boolean,
    val bordeGordo: Boolean,
    val satisfaccion:Int
)
