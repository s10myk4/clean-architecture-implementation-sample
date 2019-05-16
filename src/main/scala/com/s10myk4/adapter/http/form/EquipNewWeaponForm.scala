package com.s10myk4.adapter.http.form

import com.s10myk4.adapter.http.form.formatter.WeaponFormatter
import com.s10myk4.application.usecase.EquipNewWeaponToWarrior.EquipNewWeaponToWarriorInput
import com.s10myk4.domain.model.weapon.Weapon
import play.api.data.Form
import play.api.data.Forms._

private[http] object EquipNewWeaponForm extends WeaponFormatter {

  def apply: Form[EquipNewWeaponToWarriorInput] = Form(
    mapping(
      "weapon" -> of[Weapon]
    )(EquipNewWeaponToWarriorInput.apply)(EquipNewWeaponToWarriorInput.unapply)
  )
}
