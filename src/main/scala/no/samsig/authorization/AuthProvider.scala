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
import typings.react.reactMod.ReactText

import scala.concurrent.{Future, Promise}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.timers.SetTimeoutHandle
import scala.util.Random

@react object AuthProvider {
  type Props = ReactElement

  case class State(
      status: ProgressStates = Pending,
      error: Option[String] = None,
      user: Option[User] = None,
  )

  sealed trait ProgressStates
  case object Pending extends ProgressStates
  case object Error   extends ProgressStates
  case object Success extends ProgressStates

  val component = FunctionalComponent[Props] { children =>
    val (state, setState) = useState(State())
    useEffect(
      () => {
        val (handle, futureValue) = delay(User(Random.alphanumeric.filter(_.isLetter).take(8).mkString, "user"), 500)
        futureValue.onComplete {
          case scala.util.Success(user) =>
            setState(_.copy(Success, user = Some(user)))
          case scala.util.Failure(_) =>
            setState(_.copy(Error, error = Some("Sorry, the server is reporting an error.")))
        }
        () =>
          js.timers.clearTimeout(handle)
      },
      Seq()
    )

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
