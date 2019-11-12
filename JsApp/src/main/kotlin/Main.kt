import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point
import react.dom.h1
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window


fun main() {
    window.onload = {
        render(document.getElementById("root")!!) {
            h1 { +"Hello World!e" }

//            child(App::class) {}
        }
    }
}
