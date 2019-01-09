package com.s10myk4.adapter.http.form.formatter

import com.s10myk4.domain.model.weapon.Weapon
import play.api.data.FormError

private[http] trait WeaponFormatter {

  import play.api.data.format.Formats._
  import play.api.data.format.Formatter

  implicit val weaponFormatter: Formatter[Weapon] = new Formatter[Weapon] {
    override val format = Some(("format.weapon", Nil))

    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], Weapon] =
      parsing(Weapon.indexOf(_).get, "error.invalidFormat", Nil)(key, data)

    override def unbind(key: String, value: Weapon) = Map(key -> value.toString)
  }
}
