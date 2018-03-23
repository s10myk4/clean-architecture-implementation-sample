import com.softwaremill.macwire.wire
import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, LoggerConfigurator}
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.filters.headers.SecurityHeadersComponents
import play.filters.hosts.AllowedHostsComponents
import router.Routes

class CustomApplicationLoader extends ApplicationLoader {

  override def load(context: ApplicationLoader.Context): Application = {
    new Components(context).application
  }

  class Components(context: Context)
    extends BuiltInComponentsFromContext(context)
      with ControllerModule
      with RepositoryModule
      with ServiceModule
      //with AhcWSComponents
      with CustomHttpFiltersComponents {

    val routePrefix: String = "/"

    override def router: Router = wire[Routes]


    //setup logger
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
    }

    //example setup connection pool
    //wire[scalikejdbc.PlayInitializer]
  }

  trait CustomHttpFiltersComponents extends SecurityHeadersComponents with AllowedHostsComponents {
    def httpFilters: Seq[EssentialFilter] = Seq(securityHeadersFilter, allowedHostsFilter)
  }

}
