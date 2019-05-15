package com.s10myk4

import adapter.datasource.DatasourceComponent
import adapter.http.HttpComponent
import application.usecase.UseCaseComponent
import play.api.ApplicationLoader.Context
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, LoggerConfigurator}
import play.filters.headers.SecurityHeadersComponents
import play.filters.hosts.AllowedHostsComponents
import router.Routes

final class CustomApplicationLoader extends ApplicationLoader {

  override def load(context: ApplicationLoader.Context): Application = {
    new Components(context).application
  }

  class Components(context: Context)
      extends BuiltInComponentsFromContext(context)
      with SecurityHeadersComponents
      with AllowedHostsComponents
      with HttpComponent
      with UseCaseComponent
      with DatasourceComponent {

    val routePrefix: String = "/"

    override def router: Router = new Routes(
      httpErrorHandler,
      warriorController,
      routePrefix
    )

    lazy val httpFilters: Seq[EssentialFilter] =
      Seq(securityHeadersFilter, allowedHostsFilter)

    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
    }
  }

}
