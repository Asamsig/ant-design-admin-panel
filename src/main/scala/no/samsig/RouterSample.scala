//package no.samsig
//import slinky.core._
//import slinky.core.annotations.react
//import slinky.web.html._
//
//import typings.reactDashRouterDashDom.ReactRouterFacade._
//import typings.reactDashRouter.reactDashRouterMod._
//
//import scala.scalajs.js
//import scala.scalajs.js.annotation.JSImport
//
//@react object RouterSample {
//
//  type Props = Unit
//
//  private val css = AppCSS
//
//  def home = div(h2("Home"))
//
//  def about = div(h2("About"))
//
//  val component = FunctionalComponent[Props] { _ =>
//    def renderIntro = div(
//      header(className := "App-header")(
//        img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo"),
//        h1(className := "App-title")("Welcome to React (with Scala.js!)")
//      ),
//      p(className := "App-intro")(
//        "To get started, edit ", code("App.scala"), " and save to reload."
//      )
//    )
//
//    div(className := "App")(
//      renderIntro,
//      BrowserRouter(BrowserRouterProps())(
//        div(
//          ul(
//            li(Link[String](LinkProps[String](to = "/"))("Home")),
//            li(Link[String](LinkProps[String](to = "/about"))("About")),
//            li(Link[String](LinkProps[String](to = "/topics"))("Topics"))
//          ),
//          hr(),
//          Route[Unit](exact = true, path = "/", render = _ => home),
//          Route[Unit](path = "/about", render = _ => about),
//          Route[Unit](path = "/topics", render = props => Topics(props.`match`)),
//        )
//      )
//    )
//  }
//}
//
//@react object Topics {
//
//  case class Props(`match`: `match`[Unit])
//
//  val component = FunctionalComponent[Props] { props =>
//    div(
//      h2("Topics"),
//      ul(
//        li(Link[String](LinkProps[String](to = props.`match`.url + "/rendering"))("Rendering with React")),
//        li(Link[String](LinkProps[String](to = props.`match`.url + "/components"))("Components")),
//        li(Link[String](LinkProps[String](to = props.`match`.url + "/props-v-state"))("Props v. State"))
//      ),
//      hr(),
//      Route[Topic.Param](path = props.`match`.path + "/:topicId", render = props => Topic(props.`match`)),
//      Route[Unit](exact = true, path = props.`match`.path, render = _ => h3("Please select a topic")),
//    )
//  }
//}
//
//@react object Topic {
//
//  @js.native
//  trait Param extends js.Object {
//    val topicId: String = js.native
//  }
//
//  case class Props(`match`: `match`[Topic.Param])
//
//  val component = FunctionalComponent[Props] { props =>
//    div(
//      h3("Topic: " + props.`match`.params.topicId)
//    )
//  }
//}