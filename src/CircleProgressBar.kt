import java.awt.*
import java.awt.RenderingHints.KEY_ANTIALIASING
import java.awt.RenderingHints.VALUE_ANTIALIAS_ON
import java.awt.geom.AffineTransform
import java.awt.geom.Arc2D
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.math.sin

class CircleProgressBar : JPanel() {

    private lateinit var graphics: Graphics2D

    private val arc = Arc2D.Float(Arc2D.OPEN)

    private val timer: Timer

    private var angle = 0.0
    private var time = 0.0
    private var extent = 90f

    init {
        timer = Timer(1000 / 60) {
            angle %= 360.0
            angle += 10.0

            extent += sin(extent * 2)

            time += 0.05
            time %= Math.PI * 2

            repaint()
        }

        timer.initialDelay = 0
        timer.start()
    }

    override fun paint(g: Graphics?) {
        graphics = g as Graphics2D
        graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON)
        graphics.stroke = BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)

        drawBackground()
//        drawProgressBackground()
//        drawProgress()

        interesting(width / 2, height / 2)
        interesting(width / -2, height / -2)
    }

    private fun drawProgressBackground() {
        val state: AffineTransform = graphics.transform

        graphics.color = Color(0x2B3946)

        graphics.translate(x, y)
        graphics.drawRoundRect(0, 0, width, height, width, height)

        graphics.transform = state
    }

    private fun drawProgress() {
        val state: AffineTransform = graphics.transform

        graphics.color = Color(0x4F6276)

        arc.start = 0f
        arc.extent = extent
        arc.setFrameFromCenter(Point(0, 0), Point(width / 3, height / 3))

        graphics.translate(width / 2, height / 2)
        graphics.rotate(Math.toRadians(angle))
        graphics.draw(arc)

        graphics.transform = state
    }

    private fun interesting(x: Int, y: Int) {
        val state: AffineTransform = graphics.transform

        graphics.color = Color(0x2B3946)

        graphics.translate(x, y)
        graphics.drawRoundRect(0, 0, width, height, width, height)

        graphics.color = Color(0x4F6276)

        arc.start = 0f
        arc.extent = extent
        arc.setFrameFromCenter(Point(0, 0), Point(width / 2, height / 2))

        graphics.translate(width / 2, height / 2)
        graphics.rotate(Math.toRadians(angle))
        graphics.draw(arc)

        graphics.transform = state
    }

    private fun drawBackground() {
        graphics.color = Color(0x602C3847, true)

        graphics.fillRoundRect(0, 0, width, height, (width / 2.5).toInt(), (height / 2.5).toInt())
    }
}