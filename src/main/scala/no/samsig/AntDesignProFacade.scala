package no.samsig

import typings.antDashDesignDashPro.{Anon_Href, antDashDesignDashProProps, antDashDesignDashProComponents => AntDesignPro}
import typings.react.ScalableSlinky._

/**
  * Created by Alexander on 27/12/2019
  */
object AntDesignProFacade extends antDashDesignDashProProps {

  @inline def NoticeIcon: ExternalComponentP[NoticeIconProps]          = importSTComponent(AntDesignPro.NoticeIcon)
  @inline def Exception: ExternalComponentP[ExceptionProps[Anon_Href]] = importSTComponent(AntDesignPro.Exception)
  @inline def Login: ExternalComponentP[LoginProps]                    = importSTComponent(AntDesignPro.Login)
  @inline def LoginUserName: ExternalComponentP[LoginUserNameProps]    = importSTComponent(AntDesignPro.LoginUserName)
  @inline def LoginPassword: ExternalComponentP[LoginPasswordProps]    = importSTComponent(AntDesignPro.LoginPassword)
  @inline def LoginSubmit: ExternalComponentP[LoginPasswordProps]      = importSTComponent(AntDesignPro.LoginPassword)
  @inline def LoginTab: ExternalComponentP[LoginTabProps]              = importSTComponent(AntDesignPro.LoginTab)
  @inline def LoginItem: ExternalComponentP[LoginItemProps]            = importSTComponent(AntDesignPro.LoginItem)
//  @inline def AuthorizedRoute: ExternalComponentP[AuthorizedRouteProps] = importSTComponent(AntDesignPro.AuthorizedRoute)

}
