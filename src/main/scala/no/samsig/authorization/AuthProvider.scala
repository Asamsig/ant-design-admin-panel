package no.samsig.authorization

import no.samsig.model.User
import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html.{p, key => htmlKey, _}
import typings.antd.AntdFacade.{List => AntdList, Option => AntdOption, Switch => AntdSwitch, SwitchProps => AntdSwitchProps, _}
import no.samsig.{AntDesignProFacade, AntdProLayoutFacade, Assets}
import org.scalablytyped.runtime.StringDictionary
import slinky.core.facade.{React, ReactElement}
import typings.antDashDesignDashPro.antDashDesignDashProStrings
import typings.antDashDesignDashPro.libAuthorizedAuthorizedRouteMod.authority
import typings.history.historyMod.{Location, LocationState}
import typings.react.ScalableSlinky._
import typings.reactDashRouterDashDom.ReactRouterFacade.{Route, Switch, _}
import typings.react.reactMod.{CSSProperties, ComponentType, FormEvent, MouseEvent, ReactNode}
import typings.reactDashRouter.reactDashRouterMod.{RouteChildrenProps, RouteComponentProps, StaticContext}
import typings.reactDashRouterDashDom.reactDashRouterDashDomMod.useLocation
import AntdProLayoutFacade._
import no.samsig.AntDesignProFacade.{Exception, ExceptionProps}
import no.samsig.Assets.{NotFoundImage, ServerErrorImage}

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

@react object AuthProvider {
  type Props = ReactElement

  case class State(status: String, error: Option[String], user: Option[User])

  val component = FunctionalComponent[Props] { children =>
    val (state, setState) = useState(State("pending", None, None))
    useEffect(
      () =>
        delay(User("Asamsig", "user"), 150).map(user => {
          setState(_.copy("success", user = Some(user)))
//          setState(_.copy("error", error = Some("Sorry, the server is reporting an error.")))
        })
    )
    Authorized.Context.Provider(value = state.user)(
      state match {
        case State("pending", _, _) => PageLoading(PageLoadingProps(tip = "Loading content"))
        case State("error", Some(error), _) =>
          Exception(
            ExceptionProps(
              redirect = "/home",
              title = "500",
              img = ServerErrorImage.asInstanceOf[String],
              desc = error,
              `type` = antDashDesignDashProStrings.`500`,
            )
          )
        case State("success", _, Some(user)) => children
      }
    )
  }

  def delay[T](content: T, milliseconds: Int): Future[T] = {
    val p = Promise[T]()
    js.timers.setTimeout(milliseconds) {
      p.success(content)
    }
    p.future
  }

}
