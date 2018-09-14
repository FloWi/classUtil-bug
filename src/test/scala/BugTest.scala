import Bug.{Child1, Child2, SuperTrait}
import org.clapper.classutil.{ClassFinder, ClassInfo}
import org.scalatest.{FunSuite, Matchers}

class BugTest extends FunSuite with Matchers {
  test("doesn't work when run from IntelliJ IDEA") {

    val finder = ClassFinder()
    val infoMap = ClassFinder.classInfoMap(finder.getClasses())

    val subclasses = tryGetSubclasses(classOf[SuperTrait], infoMap)

    subclasses.map(_.name) should contain theSameElementsAs List(classOf[Child1], classOf[Child2]).map(_.getName)

  }

  test("works when run from IntelliJ IDEA") {

    val myClasspath = ClassFinder.classpath.filterNot(_.getName.contains("jaxb-api"))
    myClasspath.foreach(println)
    val finder = ClassFinder(myClasspath)
    val infoMap = ClassFinder.classInfoMap(finder.getClasses)

    val subclasses = tryGetSubclasses(classOf[SuperTrait], infoMap)

    subclasses.map(_.name) should contain theSameElementsAs List(classOf[Child1], classOf[Child2]).map(_.getName)
  }

  def tryGetSubclasses(clazz: Class[_], infoMap: Map[String, ClassInfo]): List[ClassInfo] = {
    try {
      val subclasses = ClassFinder.concreteSubclasses(classOf[SuperTrait], infoMap)
      subclasses.toList
    } catch {
      case throwable: Throwable =>
        Console.err.println("exception", throwable)
        throwable.printStackTrace(Console.err)
        throw throwable
    }

  }
}
