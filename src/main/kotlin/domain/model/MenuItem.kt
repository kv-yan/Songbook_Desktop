package domain.model

data class MenuItem(
    var title: String, var iconResId: String, var isSelected: Boolean = false, var action: Unit, var destination: String
)

