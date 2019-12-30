package no.samsig

import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html._
import typings.antd.AntdFacade.{List => _, _}
import AntdProLayoutFacade._
import org.scalablytyped.runtime.StringDictionary
import typings.react.ScalableSlinky._
import typings.react.reactMod.{FormEvent, MouseEvent}
import typings.antd.libNotificationMod.{default => Notification}
import typings.atAntDashDesignProDashLayout.atAntDashDesignProDashLayoutStrings
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

@react object App {

  type Props = Unit

  private val appCss = AppCSS

  val component = FunctionalComponent[Props] { _ =>
    val (isLoading, updateIsLoading) = useState(true)

    def renderIntro = Row(RowProps())(
      Col(ColProps(span = 7)),
      Col(ColProps(span = 10))(
        header(className := "App-header")(
          img(src := ReactLogo.asInstanceOf[String], height := "80px", className := "App-logo", alt := "logo"),
          h1(className := "App-title")("Welcome to React (with Scala.js!)")
        ),
        p(className := "App-intro")(
          "To get started, edit ",
          code("App.scala"),
          " and save to reload."
        )
      ),
      Col(ColProps(span = 7))
    )

//    scala.scalajs.js.timers.setTimeout(3000) { // note the absence of () =>
//      // work
//      console.log("LOADED")
//      updateIsLoading(false)
//    }

//    BasicLayout(
//      BasicLayoutProps(
    ProLayout(
      ProDashLayoutProps(
        logo = img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo").toST,
        title = "Samsig",
//        loading = isLoading,
//        contentWidth = ContentWidth.Fixed,
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
      PageHeaderWrapper(PageHeaderWrapperProps())(
//        Spin(
//          SpinProps(
//            size = antdStrings.large,
//            spinning = isLoading
//          )
//        )(
          renderIntro
//        )
      )
    )
  }
}
