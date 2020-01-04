package no.samsig

import no.samsig.App.renderIntro

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.LinkingInfo
import slinky.core._
import slinky.web.ReactDOM
import slinky.hot
import org.scalajs.dom
import slinky.web.html._
import typings.reactDashRouterDashDom.ReactRouterFacade._
import typings.antDashDesignDashPro.antDashDesignDashProStrings
import typings.react.ScalableSlinky._
import no.samsig.authorization.{AuthProvider, Contexts, AuthorizedRoute}
import org.scalajs.dom.console
import slinky.core.facade.React

@JSImport("ant-design-pro/dist/ant-design-pro.css", JSImport.Default)
@js.native
object AntDesignProCSS extends js.Object

@JSImport("antd/dist/antd.less", JSImport.Default)
@js.native
object AntDesignCSS extends js.Object

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

object Main {

  private val antDesignProCSS = AntDesignProCSS
  private val antDesignCSS    = AntDesignCSS
  private val appCss          = AppCSS

  @JSExportTopLevel("main")
  def main(): Unit = {
    if (LinkingInfo.developmentMode) {
      hot.initialize()
    }

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOM.render(
      AuthProvider(
          BrowserRouter(BrowserRouterProps())(
            Switch(SwitchProps())(
              Route[Unit](path = "/login", render = props => Login()),
              AuthorizedRoute("user", () => App(), RedirectProps(to = "/login"))
            )
        )
      ),
      container
    )
  }
}
