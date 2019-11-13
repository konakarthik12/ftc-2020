import kotlinx.html.id
import react.*
import react.dom.*

class App : RComponent<RProps, RState>() {
//    override fun componentDidMount() {
//        new Chart (myChartRef, {
//            type: "line",
//            data: {
//            //Bring in data
//            labels: ["Jan", "Feb", "March"],
//            datasets: [
//            {
//                label: "Sales",
//                data: [86, 67, 91],
//            }
//            ]
//        },
//            options: {
//            //Customize chart options
//        }
//        });
//    }

    override fun RBuilder.render() {
        canvas {
            attrs.id = "myChart"
            attrs.height = "600"
            attrs.width = "600"
            key = "chart"

        }
        p {
            +"yes"
        }
    }
}