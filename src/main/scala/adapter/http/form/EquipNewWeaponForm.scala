package adapter.http.form

import adapter.http.form.formatter.WeaponFormatter
import application.usecase.EquipNewWeaponToWarrior.EquipNewWeaponToWarriorInput
import domain.model.weapon.Weapon
import play.api.data.Form
import play.api.data.Forms._

private[http] object EquipNewWeaponForm
  extends WeaponFormatter {

  def apply: Form[EquipNewWeaponToWarriorInput] = Form(
    mapping(
      "warriorId" -> longNumber,
      "weapon" -> of[Weapon],
    )(EquipNewWeaponToWarriorInput.apply)(EquipNewWeaponToWarriorInput.unapply)
  )
}

