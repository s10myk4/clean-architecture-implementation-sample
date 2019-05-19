package com.s10myk4.adapter.http.form

import com.s10myk4.adapter.http.form.formatter.WeaponFormatter
import com.s10myk4.application.usecase.EquipWeaponToWarrior.EquipWeaponToWarriorInput
import com.s10myk4.domain.model.weapon.Weapon
import play.api.data.Form
import play.api.data.Forms._

private[http] object EquipWeaponForm extends WeaponFormatter {

  def apply: Form[EquipWeaponToWarriorInput] = Form(
    mapping(
      "weapon" -> of[Weapon]
    )(EquipWeaponToWarriorInput.apply)(EquipWeaponToWarriorInput.unapply)
  )
}
