sealed interface Action {
    data class SendInput(val name: String) : Action
}


data class State(
    val name: String = ""
)


fun christmasCardReducer(state: State, action: Action): State =
    when(action) {
        is Action.SendInput -> {
            state.copy(
                name = state.name
            )
        }
    }