package no.samsig

import typings.atAntDashDesignProDashLayout.{
  atAntDashDesignProDashLayoutComponents => AntdProLayout,
  atAntDashDesignProDashLayoutProps
}
import slinky.core.TagMod
import slinky.core.facade.ReactElement
import typings.antd.libFormFormMod.default.{create => createForm}
import typings.antd.libFormFormMod.{FormCreateOption, GetFieldDecoratorOptions, WrappedFormUtils}
import typings.react.ScalableSlinky._
import typings.react.reactMod.ComponentType

import scala.scalajs.js

/**
  * Created by Alexander on 27/12/2019
  */
object AntdProLayoutFacade extends atAntDashDesignProDashLayoutProps {

  @inline def ProLayout: ExternalComponentP[ProDashLayoutProps] = importSTComponent(AntdProLayout.ProDashLayout)

  @inline def BasicLayout: ExternalComponentP[BasicLayoutProps] = importSTComponent(AntdProLayout.BasicLayout)

  @inline def PageHeaderWrapper: ExternalComponentP[PageHeaderWrapperProps] = importSTComponent(AntdProLayout.PageHeaderWrapper)

}
