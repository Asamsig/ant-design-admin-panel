package no.samsig

import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html._
import typings.antd.AntdFacade.{List => AntdList, Option => AntdOption, Switch => AntdSwitch, SwitchProps => AntdSwitchProps, _}
import AntdProLayoutFacade._
import AntDesignProFacade.{Login => AntdLogin, _}
import no.samsig.Assets.{ReactLogo, ScalaJsLogo}
import slinky.core.facade.Fragment
import typings.antDashDesignDashPro.{Anon_AddTab, antDashDesignDashProStrings}
import typings.react.ScalableSlinky._
import typings.reactDashRouterDashDom.ReactRouterFacade.{Route, Switch, _}
import typings.react.reactMod.{CSSProperties, FormEvent, MouseEvent, ReactElement, ReactNode}
import typings.antd.libNotificationMod.{default => Notification}
import typings.atAntDashDesignProDashLayout.{MenuDataItemisUrlboolean, atAntDashDesignProDashLayoutStrings}
import typings.atAntDashDesignProDashLayout.libDefaultSettingsMod.ContentWidth
import typings.atAntDashDesignProDashLayout.libTypingsMod.MenuDataItem
import typings.csstype.csstypeMod.{FloatProperty, StandardLonghandProperties}

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

@react object Login {

  type Props = Unit

  val floatRight = CSSProperties(StandardLonghandProperties = StandardLonghandProperties[String | Double](float = FloatProperty.right))

  val component = FunctionalComponent[Props] { _ =>
    val (isChecked, setChecked) = useState(true)
    val stylus = js.Dynamic.literal(
      maxWidth = "360px",
      margin = "auto"
    )
    div(style := stylus)(
      AntdLogin(
        LoginProps(
//          defaultActiveKey = "tab1",
          onSubmit = (errors, values) => console.log(values)
        )
      )(
//        <Checkbox checked={this.state.autoLogin} onChange={this.changeAutoLogin}>
//        LoginTab(LoginTabProps(tabUtil = Anon_AddTab(asd => console.log(asd), asd => console.log(asd)), key = "tab1", tab = "Account"))(
        Row(RowProps())(
          Col(ColProps(span = 24))(
            header(className := "App-header")(
              img(src := ReactLogo.asInstanceOf[String], height := "160px", className := "App-logo", alt := "logo"),
              img(src := ScalaJsLogo.asInstanceOf[String], className := "Scala-logo", alt := "logo"),
              h1(className := "App-title")("Welcome to React (with Scala.js!)"),
              LoginUserName(
                LoginUserNameProps(name = "username", placeholder = "Username", `type` = "", form = "", updateActive = asd => console.log(asd))
              ),
              LoginPassword(
                LoginPasswordProps(name = "password", placeholder = "Password", `type` = "", form = "", updateActive = asd => console.log(asd))
              ),
              div(
                Checkbox(CheckboxProps(checked = isChecked, onChange = checkboxChangeEvent => {
                  setChecked(checkboxChangeEvent.target.checked.getOrElse(true))
                  console.log(checkboxChangeEvent)
                }))("Keep me logged in"),
                Link[String](
                  LinkProps[String](to = "/register", style = floatRight)
                )("Forgot password")
              ),
              Link[String](LinkProps[String](to = "/home"))(Button(ButtonProps(block = true, `type` = antdStrings.primary))("Login")),
              div(
                "Other login methods",
                Link[String](LinkProps[String](to = "/facebook"))(Icon(IconProps(`type` = "facebook"))),
                Link[String](LinkProps[String](to = "/google"))(Icon(IconProps(`type` = "google"))),
                Link[String](LinkProps[String](to = "/amazon"))(Icon(IconProps(`type` = "amazon"))),
                Link[String](
                  LinkProps[String](to = "/register", style = floatRight)
                )("Register")
              )
            )
          )
        )
      )
    )

  }

}
