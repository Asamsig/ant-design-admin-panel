package no.samsig.authorization

import no.samsig.AntDesignProFacade.{Exception, ExceptionProps}
import no.samsig.AntdProLayoutFacade._
import no.samsig.Assets.ServerErrorImage
import no.samsig.model.User
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.ReactElement
import typings.antDashDesignDashPro.antDashDesignDashProStrings
import org.scalajs.dom.console
import typings.react.reactMod.ReactText
import typings.std.Date

import scala.concurrent.{Future, Promise}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.timers.SetTimeoutHandle

@react object AuthProvider {
  type Props = ReactElement
//  type Props = Unit

  case class State(
      status: ProgressStates,
      error: Option[String],
      user: Option[User]
  )

  sealed trait ProgressStates
  case object Pending extends ProgressStates
  case object Error   extends ProgressStates
  case object Success extends ProgressStates

  val component = FunctionalComponent[Props] { children =>
    val (state, setState) = useState(State(Success, None, Some(User("Asamsig", "user"))))
//    useEffect(
//      () => {
//        val (handle, futureValue) = delay(User("Asamsig" + Date.now(), "user"), 10000)
//        futureValue.map(user => {
//          console.log(user.toString)
//          setState(_.copy(user = Some(user)))
////          setState(_.copy("error", error = Some("Sorry, the server is reporting an error.")))
//        })
//        () =>
//          js.timers.clearTimeout(handle)
//      }
//    )
//    console.log(children)
    console.log(state.toString)
    Contexts.UserContext.Provider(value = state.user)(
      state match {
        case State(Pending, _, _)       => PageLoading(PageLoadingProps(tip = "Loading content"))
        case State(Success, _, Some(_)) => children
        case State(Error, error, _) =>
          Exception(
            ExceptionProps(
              redirect = "/home",
              title = "500",
              img = ServerErrorImage.asInstanceOf[String],
              desc = error.getOrElse("Sorry, an unexpected error occurred").asInstanceOf[ReactText],
              `type` = antDashDesignDashProStrings.`500`,
            )
          )
        case State(progressState, error, _) =>
          Exception(
            ExceptionProps(
              redirect = "/home",
              title = "500",
              img = ServerErrorImage.asInstanceOf[String],
              desc = s"$progressState: " + error.getOrElse("Sorry, an unexpected error occurred").asInstanceOf[ReactText],
              `type` = antDashDesignDashProStrings.`500`,
            )
          )
      }
    )
  }

  def delay[T](content: T, milliseconds: Int): (SetTimeoutHandle, Future[T]) = {
    val p = Promise[T]()
    val handle = js.timers.setTimeout(milliseconds) {
      p.success(content)
    }
    handle -> p.future
  }

}
