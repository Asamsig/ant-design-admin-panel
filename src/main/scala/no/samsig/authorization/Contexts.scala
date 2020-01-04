package no.samsig.authorization

import no.samsig.model.User
import slinky.core.facade.{Hooks, React, ReactContext}

import scala.scalajs.js.Promise

object Contexts {

  val UserContext: ReactContext[Option[User]] = React.createContext(None)

  def useAuth = Hooks.useContext(UserContext)

}
