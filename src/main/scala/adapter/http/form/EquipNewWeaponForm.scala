package adapter.http.form

import adapter.http.form.formatter.WeaponFormatter
import domain.model.weapon.Weapon
import play.api.data.Form
import play.api.data.Forms._

final case class EquipNewWeaponForm(
  warriorId: Long,
  weapon: Weapon
)

object EquipNewWeaponForm extends WeaponFormatter {
  def apply: Form[EquipNewWeaponForm] = Form(
    mapping(
      "warriorId" -> longNumber,
      "weapon" -> of[Weapon],
    )(EquipNewWeaponForm.apply)(EquipNewWeaponForm.unapply _)
  )
}
