package adapter.http.form

import adapter.http.form.formatter.WeaponFormatter
import application.usecase.WarriorEquippedNewWeapon.EquipNewWeaponInput
import domain.model.weapon.Weapon
import play.api.data.Form
import play.api.data.Forms._

private[http] object EquipNewWeaponForm extends WeaponFormatter {
  def apply: Form[EquipNewWeaponInput] = Form(
    mapping(
      "warriorId" -> longNumber,
      "weapon" -> of[Weapon],
    )(EquipNewWeaponInput.apply)(EquipNewWeaponInput.unapply)
  )
}
