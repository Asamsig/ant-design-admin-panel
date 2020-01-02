package no.samsig.authorization

import no.samsig.model.User
import slinky.core.facade.{Hooks, React, ReactContext}

import scala.scalajs.js.Promise

object Authorized {

  val Context: ReactContext[Option[User]] = React.createContext[Option[User]](None)

  @inline def useAuth = Hooks.useContext(Context)

}
