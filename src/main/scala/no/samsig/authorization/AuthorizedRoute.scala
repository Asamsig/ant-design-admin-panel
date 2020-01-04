package no.samsig.authorization
import no.samsig.AntDesignProFacade.{Exception, ExceptionProps}
import no.samsig.Assets.{ServerErrorImage, UnauthorizedImage}
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

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@react object AuthorizedRoute {

  case class Props(
      authority: String,
      reactElement: () => ReactElement,
      redirectProps: RedirectProps
  )

  val component = FunctionalComponent[Props] { props =>
    val auth = Contexts.useAuth

    if (auth.exists(_.authority.equalsIgnoreCase(props.authority))) {
      props.reactElement.apply()
    } else {
      Exception(
        ExceptionProps(
          redirect = "/login",
          title = "403",
          img = UnauthorizedImage.asInstanceOf[String],
          desc = "Sorry, you are not authorized to access this page.",
          `type` = antDashDesignDashProStrings.`403`,
        )
      )
      Redirect(props.redirectProps)
    }
  }

}
