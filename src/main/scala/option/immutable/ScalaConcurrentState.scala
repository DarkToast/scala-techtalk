package option.immutable

object ScalaConcurrentState extends App {

  var map: Map[Int,String] = Map()

  def valueFor(key: Int): Option[String] = {
    map.get(key)
  }

  def addValue(key: Int, value: String) = map synchronized {
    // Creates a new map and does not effect the internal state.
    // Concurrent reading is still possible
    map = map + (key -> value)
  }
}
