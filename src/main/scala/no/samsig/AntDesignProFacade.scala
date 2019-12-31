package no.samsig

import typings.antDashDesignDashPro.{antDashDesignDashProProps, antDashDesignDashProComponents => AntDesignPro}
import typings.react.ScalableSlinky._

/**
  * Created by Alexander on 27/12/2019
  */
object AntDesignProFacade extends antDashDesignDashProProps {

  @inline def NoticeIcon: ExternalComponentP[NoticeIconProps] = importSTComponent(AntDesignPro.NoticeIcon)

}
