import java.awt.Color
import java.awt.Dimension
import java.awt.Rectangle
import javax.swing.JFrame
import javax.swing.JPanel

class Frame : JFrame() {
    private val container = JPanel(null)

    init {
        val cpb = CircleProgressBar()

        container.size = Dimension(300, 300)
        container.background = Color(0x242F3D)

        contentPane = container
        size = Dimension(container.width, container.height)
        isVisible = true

        cpb.bounds = Rectangle(container.width / 2 - 75 / 2, container.height / 2 - 75 / 2, 75, 75)
        cpb.isOpaque = false
        cpb.background = Color(0x00000000, true)

        container.add(cpb)

        setLocationRelativeTo(null)
    }
}