package adapter.http.form.formatter

import domain.model.weapon.Weapon

private[http] trait WeaponFormatter {

  import play.api.data.format.Formats._
  import play.api.data.format.Formatter

  implicit val weaponFormatter: Formatter[Weapon] = new Formatter[Weapon] {
    override val format = Some(("format.weapon", Nil))

    override def bind(key: String, data: Map[String, String]) =
      parsing(Weapon(_).get, "error.invalidFormat", Nil)(key, data)

    override def unbind(key: String, value: Weapon) = Map(key -> value.toString)
  }
}
