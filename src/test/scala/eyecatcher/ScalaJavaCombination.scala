package eyecatcher

import javax.swing.{JPanel, JButton, JFrame}
import java.awt.event.{ActionEvent, ActionListener, WindowEvent, WindowAdapter}
import org.scalatest.FunSuite

class ScalaJavaCombination extends FunSuite {

  /**
   * We can use any java class in scala:
   */
  val frame = new JFrame("click Me!") {

    /**
     * We add an exit listener in an old java way
     */
    val exitListener = new WindowAdapter {
      override def windowClosing(event: WindowEvent) {
        System.exit(0)
      }
    }

    /**
     * Some panel and a button
     */
    val button = new JButton("click me")

    val panel = new JPanel()
    panel.add(button)

    /**
     * And some config
     */
    addWindowListener(exitListener)
    setContentPane(panel)
    setSize(200, 122)
    setVisible(true)
  }


  /**
   * Implicits can be a useful but not so obvious feature. Use them wisely.
   * Implicits automatically converts an object A to an object B if they are in your scope.
   *
   * In this case, we convert a function of 'ActionEvent to Unit (void)' to an object of
   * ActionListeners
   */
  implicit def convertActionListener(listener: (ActionEvent) => Unit): ActionListener = {
    new ActionListener {
      def actionPerformed(p1: ActionEvent) = listener(p1)
    }
  }

  test("Wen can use a boring old swing JFrame in a new scala way") {

    // Adding an action listener in the old java way
    frame.button.addActionListener(new ActionListener {
      def actionPerformed(event: ActionEvent) {
        println("button clicked in a java way")
      }
    })

    // Now with our implicit, we can use addActionListener as higher order function an pass a
    // lambda expression to it.
    frame.button.addActionListener( (event: ActionEvent) => println("button clicked in a scala way") )

    // Let us check, if the button has the correct ActionListeners
    assert(  frame.button.getActionListeners.length == 2  )
    assert(  frame.button.getActionListeners()(0).isInstanceOf[ActionListener]  )
    assert(  frame.button.getActionListeners()(1).isInstanceOf[ActionListener]  )

    while (frame.isActive) Thread.sleep(1000)
  }
}
