package com.s10myk4.adapter.http.presenter.json

import com.s10myk4.application.usecase._
import play.api.mvc.Result

trait JsonPresenter extends Presenter[UseCaseResult, Result] with JsonEncoder with WritableJsonConverter
