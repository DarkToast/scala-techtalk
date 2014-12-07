package eyecatcher

import org.scalatest.{FlatSpec, FunSuite}

/**
 * @author Klaus Lutterjohann, tarent solutions GmbH
 */
class CollectionsSpec extends FlatSpec {

  "A list" should "be constructed using its 'Companion Object'" in {
    val ints = List(1,2,3,4,5)
    assert(  ints.isInstanceOf[List[Int]]  )
    assert(  ints == List(1,2,3,4,5)  )
  }

  it can "not be changed, only reused" in {
    var initial = List(1,2)
    // does not compile:
    // foo.add(42)
    // foo.remove(1)
    var reduced = initial.drop(1)
    var reversed = initial.reverse
    var updated = initial.updated(0,42)

    assert(  initial == List(1,2)  )
    assert(  reduced == List(2)  )
    assert(  reversed == List(2,1)  )
    assert(  updated == List(42,2)  )
  }

  it can "be combined" in {
    val twos = List(2,2)
    val threes = List(3,3,3)

    assert(  twos ++ threes == List(2,2,3,3,3)  )
    assert(  twos.intersect(threes) == List() )
  }

  it should "support basic operations" in {
    val list = List(42,43,44,45)
    assert(  list.size == 4  )
    assert(  list.head == 42  )
    assert(  list.tail == List(43,44,45)  )
    assert(  ! list.isEmpty  )
    assert(  list.splitAt(2) == (List(42,43), List(44,45)))
    assert(  list.endsWith(List(44,45))  )

    assert(  List(4,1,2).sorted == List(1,2,4)  )
  }

  it should "be processed using higher order functions" in {
    val list = List(1,2,3)
    list.foreach(letter => print(letter+ "_"))

    def timesTwo(x: Int) = x * 2
    assert(  list.map(i => i * 2) == List(2,4,6)  )
    assert(  list.map(timesTwo) == List(2,4,6)  )
    assert(  list.map(_ * 2)  == List(2,4,6)  )

    assert(  list.filter(_ < 2) == List(1)  )
    assert(  list.forall(_ < 4)  )
    assert(  list.groupBy(_ % 2) == Map( (0,List(2)), (1,List(1,3))  ) )
  }

  it can "be processed using functional idioms" in {
    val list = List(1,2,3,4)

    assert(  list.foldRight(0)(_ + _) == 10  )
  }
}
