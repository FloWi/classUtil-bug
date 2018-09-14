object Bug {

  sealed trait SuperTrait
  case class Child1() extends SuperTrait
  case class Child2() extends SuperTrait

}