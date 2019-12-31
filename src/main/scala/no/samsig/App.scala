package no.samsig

import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html.{p, key => htmlKey, _}
import typings.antd.AntdFacade.{List => _, _}
import AntdProLayoutFacade._
import AntDesignProFacade._
import org.scalablytyped.runtime.StringDictionary
import typings.react.ScalableSlinky._
import typings.reactDashRouterDashDom.ReactRouterFacade.{Route, _}
import typings.reactDashRouter.reactDashRouterMod._
import typings.react.reactMod.{CSSProperties, FormEvent, MouseEvent, ReactElement, ReactNode}
import typings.antd.libNotificationMod.{default => Notification}
import typings.atAntDashDesignProDashLayout.{MenuDataItemisUrlboolean, atAntDashDesignProDashLayoutStrings}
import typings.atAntDashDesignProDashLayout.libDefaultSettingsMod.ContentWidth
import typings.atAntDashDesignProDashLayout.libTypingsMod.MenuDataItem

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@JSImport("resources/scala-js-logo.svg", JSImport.Default)
@js.native
object ScalaJsLogo extends js.Object

@react object App {

  type Props = Unit

  private val appCss = AppCSS

  val component = FunctionalComponent[Props] { _ =>
    val (isLoading, updateIsLoading) = useState(true)

    def renderIntro = Row(RowProps())(
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

//    scala.scalajs.js.timers.setTimeout(3000) { // note the absence of () =>
//      // work
//      console.log("LOADED")
//      updateIsLoading(false)
//    }

    BrowserRouter(BrowserRouterProps())(
//    BasicLayout(
//      BasicLayoutProps(
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
                key = "1",
                name = "Home",
                path = "/",
                icon = "home",
              ),
              MenuDataItem(
                key = "2",
                name = "Payment",
                path = "/payment",
                icon = "credit-card"
              ),
              MenuDataItem(
                key = "3",
                name = "Settings",
                path = "/settings",
                icon = "tool"
              )
            )
          }
        )
      )(
        PageHeaderWrapper(PageHeaderWrapperProps(title = "Home"))(
//        PageLoading(PageLoadingProps(tip = "Loading content"))(
//        Spin(
//          SpinProps(
//            size = antdStrings.large,
//            spinning = isLoading
//          )
//        )(
          Route[Unit](exact = true, path = "/", render = _ => renderIntro),
          Route[Unit](path = "/payment", render = _ => h2("PAYMENT!!!")),
          Route[Unit](path = "/settings", render = props => h3("SETTINGS")),
//        )
        )
      )
    )
  }

  private def renderMenuItem: js.Function2[MenuDataItemisUrlboolean, ReactNode, ReactNode] = {
    case (menuDataItem, defaultDom) =>
      Link[String](LinkProps[String](to = menuDataItem.path.getOrElse(null)))(defaultDom.fromST).toST
  }

  private def renderRightContent: js.Function1[AntdProLayoutFacade.BasicLayoutProps, ReactNode] = {
    basicLayoutProps: BasicLayoutProps =>
      div(
//        <span style={{ marginRight: 24 }}>
//<Badge count={1}>
//<Avatar shape="square" icon="user" />
//</Badge>
//</span>
        span(className := "spanner")(
          Badge(BadgeProps(count = 4))(
            Avatar(AvatarProps(icon = "user")),
          )
        )
//        , NoticeIcon(NoticeIconProps())
      ).toST
  }
}
