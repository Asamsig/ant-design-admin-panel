package no.samsig

import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html.{p, key => htmlKey, _}
import typings.antd.AntdFacade.{List => AntdList, Option => AntdOption, Switch => AntdSwitch, SwitchProps => AntdSwitchProps, _}
import AntdProLayoutFacade._
import AntDesignProFacade._
import no.samsig.App.Props
import Assets._
import org.scalablytyped.runtime.StringDictionary
import typings.antDashDesignDashPro.antDashDesignDashProStrings
import typings.react.ScalableSlinky._
import typings.reactDashRouterDashDom.ReactRouterFacade.{Route, Switch, _}
import typings.react.reactMod.{CSSProperties, FormEvent, MouseEvent, ReactElement, ReactNode}
import typings.antd.libNotificationMod.{default => Notification}
import typings.atAntDashDesignProDashLayout.{MenuDataItemisUrlboolean, atAntDashDesignProDashLayoutStrings}
import typings.atAntDashDesignProDashLayout.libDefaultSettingsMod.ContentWidth
import typings.atAntDashDesignProDashLayout.libTypingsMod.MenuDataItem
import typings.history.historyMod.LocationState
import typings.reactDashRouterDashDom.reactDashRouterDashDomMod.useLocation

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react object App {

  type Props = Unit

  val component = FunctionalComponent[Props] { _ =>
//    val (isLoading, updateIsLoading) = useState(true)
    val location                     = useLocation[String]()

//    scala.scalajs.js.timers.setTimeout(3000) { // note the absence of () =>
//      // work
//      console.log("LOADED")
//      updateIsLoading(false)
//    }

    ProLayout(
      ProDashLayoutProps(
        logo = img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo").toST,
        title = "Samsig",
        disableMobile = true,
        fixedHeader = true,
        fixSiderbar = true,
        disableContentMargin = true,
//        loading = isLoading,
//        contentWidth = ContentWidth.Fixed,
        rightContentRender = renderRightContent,
        menuItemRender = renderMenuItem,
        menuDataRender = { _ =>
          js.Array(
            MenuDataItem(
              name = "Home",
              path = "/home",
              icon = "home",
            ),
            MenuDataItem(
              name = "Payment",
              path = "/payment",
              icon = "credit-card"
            ),
            MenuDataItem(
              name = "Settings",
              path = "/settings",
              icon = "tool"
            )
          )
        }
      )
    )(
      PageHeaderWrapper(
        PageHeaderWrapperProps(
          title = location.pathname.drop(1).capitalize
        )
      )(
//        PageLoading(PageLoadingProps(tip = "Loading content"))(
//        Spin(
//          SpinProps(
//            size = antdStrings.large,
//            spinning = isLoading
//          )
//        )(
        Switch(SwitchProps())(
          Route[Unit](path = "/home", render = props => renderIntro),
          Route[Unit](path = "/payment", render = props => h2("PAYMENT!!!")),
          Route[Unit](path = "/settings", render = props => h3("SETTINGS")),
          Route[Unit](
            render = props =>
              Exception(
                ExceptionProps(
                  redirect = "/home",
                  title = "404",
                  img = NotFoundImage.asInstanceOf[String],
                  desc = "Sorry, the page you visited does not exist.",
                  `type` = antDashDesignDashProStrings.`404`,
                )
            )
          )
        )
      )
    )
  }

  private def renderIntro = {
    Row(RowProps())(
      Col(ColProps(span = 24))(
        header(className := "App-header")(
          img(src := ReactLogo.asInstanceOf[String], height := "160px", className := "App-logo", alt := "logo"),
          img(src := ScalaJsLogo.asInstanceOf[String], className := "Scala-logo", alt := "logo"),
          h1(className := "App-title")("Welcome to React (with Scala.js!)"),
          p(className := "App-intro")(
            "To get started, edit ",
            code("App.scala"),
            " and save to reload."
          )
        )
      ),
    )
  }

  private def renderMenuItem: js.Function2[MenuDataItemisUrlboolean, ReactNode, ReactNode] = {
    case (menuDataItem, defaultDom) =>
      Link[String](LinkProps[String](to = menuDataItem.path.getOrElse(null)))(defaultDom.fromST).toST
  }

  private def renderRightContent: js.Function1[AntdProLayoutFacade.BasicLayoutProps, ReactNode] = { basicLayoutProps: BasicLayoutProps =>
    div(
      span(className := "spanner")(
        Badge(BadgeProps(overflowCount = 1))(
          Avatar(AvatarProps(icon = "user")),
        )
      )
//        , NoticeIcon(NoticeIconProps())
    ).toST
  }
}
