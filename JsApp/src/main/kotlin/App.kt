import react.*
import react.dom.*

class App: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        p {
            +"yes"
        }
    }
}