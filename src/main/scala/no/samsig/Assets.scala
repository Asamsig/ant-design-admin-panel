package no.samsig

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Assets {

  @JSImport("resources/logo.svg", JSImport.Default)
  @js.native
  object ReactLogo extends js.Object

  @JSImport("resources/scala-js-logo.svg", JSImport.Default)
  @js.native
  object ScalaJsLogo extends js.Object

  @JSImport("resources/not-found.svg", JSImport.Default)
  @js.native
  object NotFoundImage extends js.Object

  @JSImport("resources/server-error.svg", JSImport.Default)
  @js.native
  object ServerErrorImage extends js.Object

  @JSImport("resources/unauthorized.svg", JSImport.Default)
  @js.native
  object UnauthorizedImage extends js.Object

  @JSImport("resources/sample-avatar.png", JSImport.Default)
  @js.native
  object SampleAvatar extends js.Object

}
